package ru.ikon.trainingdairy.ui.measure

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.app
import ru.ikon.trainingdairy.databinding.FragmentMeasureBinding
import ru.ikon.trainingdairy.domain.model.MeasureModel
import ru.ikon.trainingdairy.domain.model.ParameterModel
import ru.ikon.trainingdairy.ui.MainActivity
import ru.ikon.trainingdairy.ui.measure.recycler.OnDeleteButtonClickListener
import ru.ikon.trainingdairy.ui.measure.recycler.ParameterAdapter
import ru.ikon.trainingdairy.ui.parameters.ParametersFragment
import ru.ikon.trainingdairy.utils.ARG_DATE
import ru.ikon.trainingdairy.utils.ARG_ID
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MeasureFragment : Fragment(), MeasureContract.View,
    OnDeleteButtonClickListener {

    @Inject
    lateinit var presenter: MeasureContract.Presenter

    private var _binding: FragmentMeasureBinding? = null
    private val binding: FragmentMeasureBinding
        get() {
            return _binding!!
        }

    private val adapter = ParameterAdapter()

    private var measureId: Long = 0
    private lateinit var date: Date

    /** Календарь, который будет использован для выбора даты  */
    private var calendar = Calendar.getInstance()

    companion object {
        @JvmStatic
        fun newInstance(measureId: Long, date: Long): Fragment {
            return MeasureFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_ID, measureId)
                    putLong(ARG_DATE, date)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            measureId = it.getLong(ARG_ID)
            val dateMillis = it.getLong(ARG_DATE)
            date = Date(dateMillis)
        }
    }

    override fun onResume() {
        super.onResume()
        //adapter.setData(presenter.getParameters(measureId)) // TODO: Вот это убрать. Вместо этого - получение полных данных об измерении
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).showActionBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireContext().app.di.inject(this)

        presenter.attach(this)

        _binding = FragmentMeasureBinding.inflate(inflater, container, false)

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
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(
            true
        )
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(
            true
        )

        // Для отображения меню (которое в нашем случае состоит только из одного пункта - сохранить)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onCreate(measureId, date)

        binding.editTextDate.setOnClickListener {
            DatePickerDialog(requireContext(), { _, year, month, day ->
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = month
                calendar[Calendar.DAY_OF_MONTH] = day

                val sdf = SimpleDateFormat(getString(R.string.date_format))
                val dateFormatted = sdf.format(calendar.time)
                binding.editTextDate.setText(dateFormatted)

                date = GregorianCalendar(year, month, day).time

                }, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.fab.setOnClickListener {
            if (measureId.toInt() == 0) {
                val comment = binding.editTextComment.text.toString()
                measureId = presenter.onSaveMeasure(date, measureId, comment)
            }
            startFragment(ParametersFragment.newInstance(measureId))
        }

        adapter.setOnDeleteButtonClickListener(this)
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
        } else if (item.itemId == R.id.action_save) {
            val comment = binding.editTextComment.text.toString()
            presenter.onSaveMeasure(date, measureId, comment)

            (activity as AppCompatActivity)
                .supportFragmentManager
                .popBackStack()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDeleteButtonClick(data: ParameterModel) {
        showDeleteConfirmationDialog(data)
    }

    /**
     * Отображает диалог подтверждения удаления для данного параметра
     * @param parameter Удаляемый параметр
     */
    private fun showDeleteConfirmationDialog(parameter: ParameterModel) {
        // Создаём AlertDialog.Builder и устанавливаем сообщение и обработчики нажатий
        // для положительной и отрицательной кнопок
        val builder = AlertDialog.Builder(context)
        builder.setMessage(getString(R.string.measure_delete_dialog_message))
        builder.setPositiveButton(
            getText(R.string.delete_dialog_positive_button)
        ) { _, _ ->
            presenter.onParameterDeleted(parameter.id, measureId)
        }
        builder.setNegativeButton(
            getText(R.string.delete_dialog_negative_button)
        ) { dialog, id -> // При нажатии кнопки "Отмена" закрываем диалог
            dialog?.dismiss()
        }

        // Создаём и показываем AlertDialog
        val alertDialog = builder.create()
        alertDialog.show()
    }

    override fun showData(data: MeasureModel) {
        binding.editTextComment.setText(data.comment.toString())

        val sdf = SimpleDateFormat(getString(R.string.date_format))
        val dateFormatted = sdf.format(data.date)
        binding.editTextDate.setText(dateFormatted)

        showParameters(data.parametersList)
    }

    override fun showParameters(parametersList: List<ParameterModel>) {
        if (parametersList.isEmpty()) {
            binding.emptyTitleText.visibility = View.VISIBLE
        } else {
            binding.emptyTitleText.visibility = View.GONE
        }
        binding.listViewParameters.adapter = adapter.apply {
            setData(parametersList)
        }
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun startFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.fragment_holder, fragment)
            .commit()
    }

}