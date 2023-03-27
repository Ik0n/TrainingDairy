package ru.ikon.trainingdairy.ui.userparameters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.ikon.trainingdairy.app
import ru.ikon.trainingdairy.databinding.FragmentUserParametersBinding
import javax.inject.Inject

class UserParametersFragment : Fragment(), UserParametersContract.View {

    private var _binding: FragmentUserParametersBinding? = null
    private val binding: FragmentUserParametersBinding get() { return _binding!! }

    @Inject
    lateinit var presenter: UserParametersContract.Presenter

    private lateinit var listener : ReadyButtonClickListener
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        requireContext().app.di.inject(this)
        presenter.onCreate()

        return FragmentUserParametersBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            actionReadyButton.setOnClickListener {
                if (nameEditText.text.toString() != "" &&
                    ageEditText.text.toString() != "" &&
                    weightEditText.text.toString() != "") {
                    savePreferences(
                        nameEditText.text.toString(),
                        ageEditText.text.toString(),
                        weightEditText.text.toString()
                    )
                    onReadyButtonClick(this@UserParametersFragment.parentFragmentManager)
                    listener.readyButtonClick()
                }
            }
        }
    }

    companion object {
        const val APP_PREFERENCES = "mysettings"
        const val APP_PREFERENCES_NAME = "name"
        const val APP_PREFERENCES_AGE = "age"
        const val APP_PREFERENCES_WEIGHT = "weight"

        @JvmStatic
        fun newInstance() : Fragment {
            return UserParametersFragment()
        }
    }

    override fun showData(data: String) {

    }

    override fun onReadyButtonClick(manager: FragmentManager) {
        presenter.onReadyButtonClick(manager)
    }

    override fun savePreferences(name: String, age: String, weight: String) {
        presenter.savePreferences(name, age, weight)
    }

    fun setReadyButtonClickListener(listener : ReadyButtonClickListener) {
        this.listener = listener
    }
}