package ru.ikon.trainingdairy.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import ru.ikon.trainingdairy.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding: ActivitySplashBinding get() { return _binding!! }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.logotype) {
            this
                .animate()
                .scaleX(scaleX * 2)
                .scaleY(scaleY * 2)
                .setDuration(3000L)
                .start()
        }

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000L)
    }

}