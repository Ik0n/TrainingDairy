package ru.ikon.trainingdairy.ui.userparameters

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.databinding.FragmentUserParametersBinding

class UserParametersFragment : Fragment() {

    private var _binding: FragmentUserParametersBinding? = null
    private val binding: FragmentUserParametersBinding get() { return _binding!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
                    context?.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)?.edit {
                        putString(APP_PREFERENCES_NAME, nameEditText.text.toString())
                        putString(APP_PREFERENCES_AGE, ageEditText.text.toString())
                        putString(APP_PREFERENCES_WEIGHT, weightEditText.text.toString())
                        listener?.readyButtonClick()
                    }
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

    interface ReadyButtonClickListener {
        fun readyButtonClick()
    }

    private var listener : ReadyButtonClickListener? = null

    public fun setReadyButtonClickListener(listener : ReadyButtonClickListener) {
        this.listener = listener
    }
}