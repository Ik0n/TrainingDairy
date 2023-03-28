package ru.ikon.trainingdairy.ui.userparameters

import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit
import androidx.fragment.app.FragmentManager
import ru.ikon.trainingdairy.App
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository
import ru.ikon.trainingdairy.ui.base.BasePresenter
import ru.ikon.trainingdairy.ui.programslist.ProgramsListFragment
import ru.ikon.trainingdairy.ui.userparameters.UserParametersFragment.Companion.APP_PREFERENCES

class UserParametersPresenter(repository: DiaryEntryRepository) : UserParametersContract.Presenter, BasePresenter<UserParametersContract.View>(
    repository
) {

    override fun onCreate() {

    }

    override fun onReadyButtonClick(manager: FragmentManager) {
        manager
            .beginTransaction()
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