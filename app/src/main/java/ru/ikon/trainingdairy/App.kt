package ru.ikon.trainingdairy

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.di.DaggerMyComponent
import ru.ikon.trainingdairy.di.DbModule
import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl

class App : Application() {

    companion object {
        var instance : App? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val di by lazy {
        DaggerMyComponent.builder().dbModule(DbModule()).build()
    }

}

val Context.app: App
    get() = applicationContext as App