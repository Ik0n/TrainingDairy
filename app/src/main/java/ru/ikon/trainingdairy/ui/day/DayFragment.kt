package ru.ikon.trainingdairy.ui.day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.databinding.FragmentDayBinding
import ru.ikon.trainingdairy.domain.model.DiaryEntryModel
import ru.ikon.trainingdairy.domain.model.TrainingModel
import ru.ikon.trainingdairy.ui.day.recycler.EntryCardAdapter
import ru.ikon.trainingdairy.ui.month.MonthFragment
import java.util.*
import kotlin.collections.ArrayList

class DayFragment : Fragment(), DayContract.View {

    private lateinit var presenter: DayContract.Presenter

    private var _binding: FragmentDayBinding? = null
    private val binding: FragmentDayBinding get() { return _binding!! }

    private val adapter = EntryCardAdapter()

    companion object {
        @JvmStatic
        fun newInstance() : Fragment {
            return DayFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = DayPresenter()
        presenter.attach(this)

        _binding = FragmentDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener("DATE", this) { key, bundle ->
            binding.dateTextView.text = bundle.getString("DATE", "error").toString()

            binding.recyclerView.adapter = adapter.apply {
                setData(bundle.get("EVENTS") as MutableList<DiaryEntryModel>)
            }
        }


    }


    override fun showData(data: ArrayList<DiaryEntryModel>) {
        TODO("Not yet implemented")
    }
}