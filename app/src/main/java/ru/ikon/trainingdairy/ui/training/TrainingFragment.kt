package ru.ikon.trainingdairy.ui.training

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.app
import ru.ikon.trainingdairy.databinding.FragmentTrainingBinding
import ru.ikon.trainingdairy.domain.model.ExerciseModel
import ru.ikon.trainingdairy.domain.model.TrainingModel
import ru.ikon.trainingdairy.ui.MainActivity
import ru.ikon.trainingdairy.ui.exercise.ExerciseFragment
import ru.ikon.trainingdairy.ui.exerciseattempts.ExerciseAttemptsFragment
import ru.ikon.trainingdairy.ui.history.HistoryFragment
import ru.ikon.trainingdairy.ui.training.recycler.ExerciseTrainingAdapter
import ru.ikon.trainingdairy.ui.training.recycler.OnDeleteButtonClickListener
import ru.ikon.trainingdairy.ui.training.recycler.OnHistoryButtonClickListener
import ru.ikon.trainingdairy.ui.training.recycler.OnItemClickListener
import ru.ikon.trainingdairy.utils.ARG_DATE
import ru.ikon.trainingdairy.utils.ARG_ID
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class TrainingFragment : Fragment(), TrainingContract.View, OnDeleteButtonClickListener, OnItemClickListener, OnHistoryButtonClickListener {
    private var trainingId: Long = 0
    private lateinit var trainingDate: Date

    @Inject
    lateinit var presenter: TrainingContract.Presenter

    private var _binding: FragmentTrainingBinding? = null
    private val binding: FragmentTrainingBinding
        get() {
            return _binding!!
        }

    //private val adapter = ExerciseTrainingAdapter(this?.context)

    private lateinit var adapter: ExerciseTrainingAdapter



    /** Календарь, который будет использован для выбора даты  */
    private var calendar = Calendar.getInstance()

    private var currentDate = Date()

    /** Модель тренировки, данные которой отображаются на экране  */
    var mTraining: TrainingModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            trainingId = it.getLong(ARG_ID)
            val dateMillis = it.getLong(ARG_DATE)
            trainingDate = Date(dateMillis)
        }
        adapter = ExerciseTrainingAdapter(this.requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireContext().app.di.inject(this)
        presenter.attach(this)

        _binding = FragmentTrainingBinding.inflate(inflater, container, false)

        initializeActionBar()

        return binding.root
    }

    /**
     * Добавляет на панель действий иконку "Назад"
     * и инициализирует текстовые поля, находящиеся на ней
     */
    private fun initializeActionBar() {
        // Поскольку этот фрагмент имеет свой собственный Toolbar с тремя полями,
        // при открытии этого фрагмента мы обращаемся к Activity и скрываем у неё основной Toolbar
        (activity as MainActivity).hideActionBar()

        // Устанавливаем наш кастомный Toolbar в качестве SupportActionBar,
        // чтобы отобразить на нём кнопки Назад и Сохранить
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        // Для отображения системной кнопки Назад
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        // Для отображения меню (которое в нашем случае состоит только из одного пункта - сохранить)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onCreate(trainingId, trainingDate)

        with(binding) {
            dateLayout.setOnClickListener {
                onDateEditTextClicked()
            }

            editTextDate.setOnClickListener {
                onDateEditTextClicked()
            }
            fab.setOnClickListener {
                if (trainingId != 0L) {
                    startFragment(ExerciseFragment.newInstance(trainingId))
                } else {
                    trainingId = presenter.saveTraining(
                        name = editTextName.text.toString(),
                        date = trainingDate,
                        comment = editTextComment.text.toString()
                    )
                    startFragment(ExerciseFragment.newInstance(trainingId))
                }
            }
            adapter.setOnDeleteButtonClickListener(this@TrainingFragment)
            adapter.setOnItemClickListener(this@TrainingFragment)
            adapter.setOnHistoryButtonClickListener(this@TrainingFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Используем меню menu_save, в котором присутствует только один пункт - Сохранить
        inflater.inflate(R.menu.menu_save, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            // При нажатии на кнопку Назад "закрываем" текущий фрагмент, удаляя его из бэк-стека
            (activity as AppCompatActivity)
                .supportFragmentManager
                .popBackStack()
        }
        if (item.itemId == R.id.action_save) {
            if (trainingId != 0L) {
                presenter.updateTraining(trainingId, binding.editTextName.text.toString(), trainingDate, binding.editTextComment.text.toString())
            } else {
                presenter.saveTraining(
                    name = binding.editTextName.text.toString(),
                    date = trainingDate,
                    comment = binding.editTextComment.text.toString()
                )
            }
            (activity as AppCompatActivity)
                .supportFragmentManager
                .popBackStack()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showData(training: TrainingModel) {
        // Приводим дату к строке для отображения
        // и выводим на экран в элемент
        val outputFormat = SimpleDateFormat(getString(R.string.date_format))
        val outputDateString = outputFormat.format(training.date)

        with(binding) {

            if (training.exerciseList.isEmpty()) {
                emptyTitleText.visibility = View.VISIBLE
            } else {
                emptyTitleText.visibility = View.GONE
            }

            editTextName.setText(training.name)
            editTextDate.setText(outputDateString)
            editTextComment.setText(training.comment)
            recyclerViewExercises.adapter = adapter.apply {
                setData(training.exerciseList)
            }
        }
    }

    override fun showExercises(exerciseList: List<ExerciseModel>) {
        if (exerciseList.isEmpty()) {
            binding.emptyTitleText.visibility = View.VISIBLE
        } else {
            binding.emptyTitleText.visibility = View.GONE
        }
        adapter.apply {
            setData(exerciseList)
        }
    }

    /**
     * Обработчик нажатия на поле выбора даты
     */
    private fun onDateEditTextClicked() {
        with(binding) {
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    calendar[Calendar.YEAR] = year
                    calendar[Calendar.MONTH] = monthOfYear
                    calendar[Calendar.DAY_OF_MONTH] = dayOfMonth

                    val sdf = SimpleDateFormat(getString(R.string.date_format))
                    editTextDate.setText(sdf.format(calendar.time))

                    trainingDate = GregorianCalendar(year, monthOfYear, dayOfMonth).time
                }

            DatePickerDialog(
                requireContext(),
                dateSetListener,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    override fun onStop() {
        super.onStop()
        // При закрытии этого фрагмента снова отображаем у Activity основной Toolbar
        (activity as MainActivity).showActionBar()
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detach()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = TrainingFragment()

        @JvmStatic
        fun newInstance(trainingId: Long, date: Long) =
            TrainingFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_ID, trainingId)
                    putLong(ARG_DATE, date)
                }
            }
    }

    private fun startFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.fragment_holder, fragment)
            .commit()
    }

    override fun onDeleteButtonClick(data: ExerciseModel) {
        showDeleteConfirmationDialog(data)
    }

    /**
     * Отображает диалог подтверждения удаления для данного упражнения
     * @param exercise Удаляемое упражнение
     */
    private fun showDeleteConfirmationDialog(exercise: ExerciseModel) {
        // Создаём AlertDialog.Builder и устанавливаем сообщение и обработчики нажатий
        // для положительной и отрицательной кнопок
        val builder = AlertDialog.Builder(context)
        builder.setMessage(getString(R.string.training_fragment_delete_dialog_message))
        builder.setPositiveButton(
            getString(R.string.delete_dialog_positive_button)
        ) { _, _ ->
            // При нажатии кнопки "Удалить" оповещаем презентер
            presenter.onExerciseDeleted(exercise.id, trainingId)
        }
        builder.setNegativeButton(
            getString(R.string.delete_dialog_negative_button)
        ) { dialog, id -> // При нажатии кнопки "Отмена" закрываем диалог
            dialog?.dismiss()
        }

        // Создаём и показываем AlertDialog
        val alertDialog = builder.create()
        alertDialog.show()
    }

    override fun onItemClick(item: ExerciseModel) {
        startFragment(ExerciseAttemptsFragment.newInstance(item.trainingId, item.id))
    }

    override fun onHistoryButtonClick(data: ExerciseModel) {
        startFragment(HistoryFragment.newInstance(data.name.toString()))
    }
}