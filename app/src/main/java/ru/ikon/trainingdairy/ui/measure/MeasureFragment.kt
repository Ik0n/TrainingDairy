package ru.ikon.trainingdairy.ui.measure

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.databinding.FragmentMeasureBinding
import java.util.*

class MeasureFragment : Fragment(), MeasureContract.View {

    private lateinit var presenter : MeasureContract.Presenter

    private var _binding: FragmentMeasureBinding? = null
    private val binding: FragmentMeasureBinding get() { return _binding!! }

    companion object {
        @JvmStatic
        private lateinit var isMeasureFragmentListener: IsMeasureFragmentListener

        @JvmStatic
        fun newInstance() : Fragment {
            return MeasureFragment()
        }

        @JvmStatic
        fun setIsMeasureFragmentListener(isMeasureFragmentListener: IsMeasureFragmentListener) {
            this.isMeasureFragmentListener = isMeasureFragmentListener
        }
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

        binding.editTextDate.setOnClickListener {
            DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, day ->
                binding.editTextDate.setText("" + day + "/" + month + "/" + year)
            }, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        isMeasureFragmentListener.isMeasureFragment()
    }

    override fun showData() {

    }

}