package ru.ikon.trainingdairy.ui.training

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.databinding.FragmentTrainingBinding
import ru.ikon.trainingdairy.domain.model.TrainingModel
import ru.ikon.trainingdairy.ui.MainActivity
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_ID = "id"
private const val ARG_DATE = "date"

class TrainingFragment : Fragment(), TrainingContract.View {
    private var trainingId: Long = 0
    private lateinit var trainingDate: Date

    private lateinit var presenter: TrainingContract.Presenter

    private var _binding: FragmentTrainingBinding? = null
    private val binding: FragmentTrainingBinding
        get() {
            return _binding!!
        }

    /** Календарь, который будет использован для выбора даты  */
    private var mCalendar = Calendar.getInstance()

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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        presenter = TrainingPresenter()
        presenter.attach(this)

        _binding = FragmentTrainingBinding.inflate(inflater, container, false)

        initializeActionBar()

        if (trainingId == 0L) {
            // Создаём новую тренировку
            mTraining = TrainingModel(trainingDate)

            // Приводим дату к строке для отображения
            // и выводим на экран в элемент mDateEditText
            val outputFormat = SimpleDateFormat("dd.MM.yyyy")
            val outputDateString = outputFormat.format(trainingDate)
            binding.editTextDate.setText(outputDateString)
        } else {
            // Загружаем уже существующую тренировку из репозитория

        }

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

        return super.onOptionsItemSelected(item)
    }

    override fun showData(data: TrainingModel) {
        // Приводим дату к строке для отображения
        // и выводим на экран в элемент
        val outputFormat = SimpleDateFormat("dd.MM.yyyy")
        val outputDateString = outputFormat.format(data.date)

        with(binding) {
            editTextName.setText(data.name)
            editTextDate.setText(outputDateString)
            editTextComment.setText(data.comment)
        }
    }

    /**
     * Обработчик нажатия на поле выбора даты
     */
    private fun onDateEditTextClicked() {
        with(binding) {
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    mCalendar[Calendar.YEAR] = year
                    mCalendar[Calendar.MONTH] = monthOfYear
                    mCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth

                    val sdf = SimpleDateFormat("dd.MM.yyyy")
                    editTextDate.setText(sdf.format(mCalendar.time))

                    currentDate =
                        GregorianCalendar(year, monthOfYear, dayOfMonth).time
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

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(trainingId: Long, date: Long) =
            TrainingFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_ID, trainingId)
                    putLong(ARG_DATE, date)
                }
            }
    }
}