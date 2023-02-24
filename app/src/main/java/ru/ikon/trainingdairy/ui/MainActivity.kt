package ru.ikon.trainingdairy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.databinding.ActivityMainBinding
import ru.ikon.trainingdairy.ui.month.MonthFragment
import ru.ikon.trainingdairy.ui.userparameters.UserParametersFragment
import ru.ikon.trainingdairy.ui.userparameters.UserParametersFragment.Companion.APP_PREFERENCES
import ru.ikon.trainingdairy.ui.userparameters.UserParametersFragment.Companion.APP_PREFERENCES_NAME

class MainActivity : AppCompatActivity(), UserParametersFragment.ReadyButtonClickListener {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() { return _binding!! }
    private var userParametersFragment = UserParametersFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Принудительно запрещаем приложению переключаться в ночной режим,
        // чтобы тема всегда была светлой
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getString(APP_PREFERENCES_NAME, "") == "") {

            binding.bottomNavigationBar.visibility = View.GONE

            userParametersFragment.setReadyButtonClickListener(this)

            startFragment(UserParametersFragment.newInstance())
        } else {
            startFragment(ProgramsListFragment.newInstance())
            initBottomNavigationBar()
        }


    }

    private fun initBottomNavigationBar() {
        binding.bottomNavigationBar.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.action_bottom_navigation_bar_programs -> startFragment(
                    ProgramsListFragment.newInstance()
                )
                R.id.action_bottom_navigation_bar_trainings -> startFragment(
                    MonthFragment.newInstance()
                )
            }

            true
        }
    }

    private fun startFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.animator.fragment_fade_in, R.animator.fragment_fade_out)
            .replace(R.id.fragment_holder, fragment)
            .commit()
    }

    override fun readyButtonClick() {
        binding.bottomNavigationBar.visibility = View.VISIBLE
        startFragment(ProgramsListFragment.newInstance())
    }
}