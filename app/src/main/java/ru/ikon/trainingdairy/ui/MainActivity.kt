package ru.ikon.trainingdairy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.databinding.ActivityMainBinding
import ru.ikon.trainingdairy.ui.month.MonthFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() { return _binding!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Принудительно запрещаем приложению переключаться в ночной режим,
        // чтобы тема всегда была светлой
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_holder,
                    ProgramsListFragment.newInstance()
                )
                .commit()
        }

        initBottomNavigationBar()
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
}