package ru.ikon.trainingdairy.ui.parameters

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.app
import ru.ikon.trainingdairy.databinding.FragmentMeasureBinding
import ru.ikon.trainingdairy.databinding.FragmentParametersBinding
import ru.ikon.trainingdairy.domain.model.ParameterModel
import ru.ikon.trainingdairy.ui.measure.MeasureFragment
import ru.ikon.trainingdairy.utils.MEASURE_ID
import javax.inject.Inject
import kotlin.random.Random

class ParametersFragment : Fragment(), ParametersContract.View {

    private var measureId: Long = 0

    @Inject
    lateinit var presenter : ParametersContract.Presenter

    private var _binding: FragmentParametersBinding? = null
    private val binding: FragmentParametersBinding get() { return _binding!! }

    companion object {

        @JvmStatic
        fun newInstance(measureId: Long) : Fragment {
            return ParametersFragment().apply {
                arguments = Bundle().apply {
                    putLong(MEASURE_ID, measureId)
                }
            }
        }
        @JvmStatic
        fun newInstance() : Fragment {
            return ParametersFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            measureId = it.getLong(MEASURE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireContext().app.di.inject(this)
        presenter.attach(this)

        _binding = FragmentParametersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeActionBar()

        presenter.onCreate(measureId)

    }

    override fun showData(data: List<ParameterModel>) {
        with(binding) {
            editText1.setText(data.find { x -> x.name == editTextLayout1.hint.toString() }?.value?.toString())
            editText2.setText(data.find { x -> x.name == editTextLayout2.hint.toString() }?.value?.toString())
            editText3.setText(data.find { x -> x.name == editTextLayout3.hint.toString() }?.value?.toString())
            editText4.setText(data.find { x -> x.name == editTextLayout4.hint.toString() }?.value?.toString())
            editText5.setText(data.find { x -> x.name == editTextLayout5.hint.toString() }?.value?.toString())
            editText6.setText(data.find { x -> x.name == editTextLayout6.hint.toString() }?.value?.toString())
            editText7.setText(data.find { x -> x.name == editTextLayout7.hint.toString() }?.value?.toString())
            editText8.setText(data.find { x -> x.name == editTextLayout8.hint.toString() }?.value?.toString())
            editText9.setText(data.find { x -> x.name == editTextLayout9.hint.toString() }?.value?.toString())
            editText10.setText(data.find { x -> x.name == editTextLayout10.hint.toString() }?.value?.toString())
        }
    }

    private fun initializeActionBar() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.setTitle(getString(R.string.parametrs_fragment_actionbar_title))
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_save, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_save -> {
                presenter.saveParametersList(measureId, createParametersList())
                parentFragmentManager.popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createParametersList(): List<ParameterModel> {
        return ArrayList<ParameterModel>().apply {
            with(binding) {
                if (editText1.text.toString() != "") {
                    add(ParameterModel(Random.nextLong(), editTextLayout1.hint.toString(), editText1.text.toString().toInt()))
                }
                if (editText2.text.toString() != "") {
                    add(ParameterModel(Random.nextLong(), editTextLayout2.hint.toString(), editText2.text.toString().toInt()))
                }
                if (editText3.text.toString() != "") {
                    add(ParameterModel(Random.nextLong(), editTextLayout3.hint.toString(), editText3.text.toString().toInt()))
                }
                if (editText4.text.toString() != "") {
                    add(ParameterModel(Random.nextLong(), editTextLayout4.hint.toString(), editText4.text.toString().toInt()))
                }
                if (editText5.text.toString() != "") {
                    add(ParameterModel(Random.nextLong(), editTextLayout5.hint.toString(), editText5.text.toString().toInt()))
                }
                if (editText6.text.toString() != "") {
                    add(ParameterModel(Random.nextLong(), editTextLayout6.hint.toString(), editText6.text.toString().toInt()))
                }
                if (editText7.text.toString() != "") {
                    add(ParameterModel(Random.nextLong(), editTextLayout7.hint.toString(), editText7.text.toString().toInt()))
                }
                if (editText8.text.toString() != "") {
                    add(ParameterModel(Random.nextLong(), editTextLayout8.hint.toString(), editText8.text.toString().toInt()))
                }
                if (editText9.text.toString() != "") {
                    add(ParameterModel(Random.nextLong(), editTextLayout9.hint.toString(), editText9.text.toString().toInt()))
                }
                if (editText10.text.toString() != "") {
                    add(ParameterModel(Random.nextLong(), editTextLayout10.hint.toString(), editText10.text.toString().toInt()))
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detach()
    }


}