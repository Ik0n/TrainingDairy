package ru.ikon.trainingdairy.ui.measure

import android.app.DatePickerDialog
import android.os.Bundle
import android.system.Os.close
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.databinding.FragmentMeasureBinding
import ru.ikon.trainingdairy.domain.model.ParameterModel
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
        (activity as AppCompatActivity).supportActionBar?.hide()

        adapter.setData(presenter.getParameters(measureId))

    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        presenter = MeasurePresenter()
        presenter.attach(this)

        _binding = FragmentMeasureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onCreate(measureId)

        arguments?.let {
            binding.emptyTitleText.visibility = View.GONE
        }

        binding.editTextDate.setOnClickListener {
            DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, day ->
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
            .setCustomAnimations(R.animator.fragment_fade_in, R.animator.fragment_fade_out)
            .addToBackStack("")
            .replace(R.id.fragment_holder, fragment)
            .commit()
    }

}