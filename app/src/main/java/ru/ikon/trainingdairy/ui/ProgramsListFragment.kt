package ru.ikon.trainingdairy.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.ikon.trainingdairy.R

class ProgramsListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_programs_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() : Fragment {
            return ProgramsListFragment()
        }
    }
}