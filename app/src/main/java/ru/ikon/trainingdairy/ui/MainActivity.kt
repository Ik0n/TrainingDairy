package ru.ikon.trainingdairy.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.databinding.ActivityMainBinding
import ru.ikon.trainingdairy.ui.month.MonthFragment
import ru.ikon.trainingdairy.ui.programslist.ProgramsListFragment
import ru.ikon.trainingdairy.ui.userparameters.ReadyButtonClickListener
import ru.ikon.trainingdairy.ui.userparameters.UserParametersFragment
import ru.ikon.trainingdairy.utils.APP_PREFERENCES
import ru.ikon.trainingdairy.utils.APP_PREFERENCES_NAME

class MainActivity : AppCompatActivity(), ReadyButtonClickListener {

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

            startFragment(userParametersFragment)

            userParametersFragment.setReadyButtonClickListener(this)

        } else {
            //binding.bottomNavigationBar.visibility = View.VISIBLE
            startFragment(MonthFragment.newInstance())
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            // При нажатии на кнопку Назад "закрываем" текущий фрагмент, удаляя его из бэк-стека
                supportFragmentManager
                .popBackStack()
        }

        return super.onOptionsItemSelected(item)
    }


    private fun startFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_holder, fragment)
            .commit()
    }

    override fun readyButtonClick() {
        binding.bottomNavigationBar.visibility = View.VISIBLE
        userParametersFragment.onReadyButtonClick(supportFragmentManager)
    }

    /**
     * Скрывает основной ActionBar у Activity. Вызывается из фрагментов,
     * которые имеют свой собственный ToolBar.
     */
    fun hideActionBar() {
        supportActionBar?.hide()
    }

    /**
     * Снова отображает основной ActionBar у Activity. Вызывается из фрагментов,
     * которые имеют свой собственный ToolBar, при их закрытии. Поскольку такие
     * фрагменты устанавливают свой собственный ToolBar в качестве SupportActionBar,
     * то здесь мы обратно устанавливаем ToolBar этой активности в качестве
     * SupportActionBar и только потом отображаем его.
     */
    fun showActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.show()
    }
}