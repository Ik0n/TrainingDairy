package ru.ikon.trainingdairy

import android.app.Application
import androidx.fragment.app.Fragment

class App : Application() {

    companion object {
        var instance : App? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}