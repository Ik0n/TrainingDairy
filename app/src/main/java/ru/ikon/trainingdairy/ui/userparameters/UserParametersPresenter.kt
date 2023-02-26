package ru.ikon.trainingdairy.ui.userparameters

import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit
import androidx.fragment.app.FragmentManager
import ru.ikon.trainingdairy.App
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.ui.programslist.ProgramsListFragment
import ru.ikon.trainingdairy.ui.userparameters.UserParametersFragment.Companion.APP_PREFERENCES

class UserParametersPresenter : UserParametersContract.Presenter {

    private var view : UserParametersContract.View? = null

    override fun attach(view: UserParametersContract.View) {
        this.view = view
    }

    override fun onCreate() {

    }

    override fun detach() {
        view = null
    }

    override fun onReadyButtonClick(manager: FragmentManager) {
        manager
            .beginTransaction()
            .setCustomAnimations(R.animator.fragment_fade_in, R.animator.fragment_fade_out)
            .replace(R.id.fragment_holder, ProgramsListFragment.newInstance())
            .commit()
    }

    override fun savePreferences(name: String, age: String, weight: String) {
        App.instance?.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)?.edit {
            putString(UserParametersFragment.APP_PREFERENCES_NAME, name)
            putString(UserParametersFragment.APP_PREFERENCES_AGE, age)
            putString(UserParametersFragment.APP_PREFERENCES_WEIGHT, weight)
        }
    }

}