package ru.ikon.trainingdairy

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.room.Room
import ru.ikon.trainingdairy.di.DaggerMyComponent
import ru.ikon.trainingdairy.di.DbModule
import ru.ikon.trainingdairy.domain.repository.room.DiaryEntryDatabase

class App : Application() {

    companion object {
        var instance: App? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val di by lazy {
        DaggerMyComponent.builder()
            .dbModule(DbModule(applicationContext))
            .build()
    }

    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            DiaryEntryDatabase::class.java, "diary-db"
        )
            .allowMainThreadQueries()
            .build()
    }
}

val Context.app: App
    get() = applicationContext as App