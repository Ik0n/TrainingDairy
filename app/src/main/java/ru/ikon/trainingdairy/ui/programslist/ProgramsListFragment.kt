package ru.ikon.trainingdairy.ui.programslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.app
import javax.inject.Inject

class ProgramsListFragment : Fragment(), ProgramsListContract.View {

    @Inject
    lateinit var presenter: ProgramsListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireContext().app.di.inject(this)
        presenter.attach(this)
        presenter.onCreate()

        return inflater.inflate(R.layout.fragment_programs_list, container, false)
    }

    override fun showData(data: String) {
        Toast.makeText(context, data, Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() : Fragment {
            return ProgramsListFragment()
        }
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


}