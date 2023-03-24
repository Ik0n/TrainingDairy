package ru.ikon.trainingdairy.ui.measure

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.databinding.FragmentMeasureBinding
import ru.ikon.trainingdairy.domain.model.ParameterModel
import ru.ikon.trainingdairy.ui.MainActivity
import ru.ikon.trainingdairy.ui.measure.recycler.OnDeleteButtonClickListener
import ru.ikon.trainingdairy.ui.measure.recycler.ParameterAdapter
import ru.ikon.trainingdairy.ui.parameters.ParametersFragment
import java.util.*

class MeasureFragment : Fragment(), MeasureContract.View, OnDeleteButtonClickListener {

    private var measureId: Long = 0

    private lateinit var presenter : MeasureContract.Presenter

    private var _binding: FragmentMeasureBinding? = null
    private val binding: FragmentMeasureBinding get() { return _binding!! }

    private val adapter = ParameterAdapter()

    private lateinit var date: Date

    companion object {

            private const val ARG_ID = "id"

            @JvmStatic
            fun newInstance(measureId: Long) : Fragment {
                return MeasureFragment().apply {
                    arguments = Bundle().apply {
                        putLong(ARG_ID, measureId)
                    }
                }
            }
            @JvmStatic
            fun newInstance() : Fragment {
                return MeasureFragment()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            measureId = it.getLong(ARG_ID)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.setData(presenter.getParameters(measureId))
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
        presenter = MeasurePresenter()
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
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        // Для отображения меню (которое в нашем случае состоит только из одного пункта - сохранить)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onCreate(measureId)

        arguments?.let {
            binding.emptyTitleText.visibility = View.GONE
        }

        binding.editTextDate.setOnClickListener {
            DatePickerDialog(requireContext(), { _, year, month, day ->
                binding.editTextDate.setText("" + day + "/" + month + "/" + year)

                date = GregorianCalendar(year, month, day).time

            }, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.fab.setOnClickListener {
            if (measureId.toInt() == 0) {
                measureId = presenter.saveMeasure(date)
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
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onClick(data: ParameterModel) {
        presenter.deleteParameter(data.id, measureId)
    }

    override fun showData(data: List<ParameterModel>) {
        binding.listViewParameters.adapter = adapter.apply {
            setData(data)
        }
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detach()
    }

    private fun startFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.fragment_holder, fragment)
            .commit()
    }

}