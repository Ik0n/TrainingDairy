package ru.ikon.trainingdairy.ui.month

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ru.ikon.trainingdairy.R

class MonthFragment : Fragment(), MonthContract.View {

    private lateinit var presenter: MonthContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = MonthPresenter()
        presenter.attach(this)
        presenter.onCreate()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_month, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() : Fragment {
            return MonthFragment()
        }
    }

    override fun showData(data: String) {
        Toast.makeText(context, data, Toast.LENGTH_SHORT).show()
    }
}