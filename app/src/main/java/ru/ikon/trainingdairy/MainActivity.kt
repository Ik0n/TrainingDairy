package ru.ikon.trainingdairy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() { return _binding!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_holder, ProgramsListFragment.newInstance())
                .commit()
        }

        initBottomNavigationBar()
    }

    private fun initBottomNavigationBar() {
        binding.bottomNavigationBar.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.action_bottom_navigation_bar_programs -> startFragment(ProgramsListFragment.newInstance())
                R.id.action_bottom_navigation_bar_trainings -> startFragment(MonthFragment.newInstance())
            }

            true
        }
    }

    private fun startFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_holder, fragment)
            .commit()
    }

}