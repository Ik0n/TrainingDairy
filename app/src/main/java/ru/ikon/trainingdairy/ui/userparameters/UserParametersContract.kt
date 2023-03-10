package ru.ikon.trainingdairy.ui.userparameters

import androidx.fragment.app.FragmentManager

class UserParametersContract {
    interface View {
        fun showData(data: String)
        fun onReadyButtonClick(manager: FragmentManager)
        fun savePreferences(name: String, age: String, weight: String)
    }

    interface Presenter {
        fun attach(view: View)
        fun onCreate()
        fun detach()
        fun onReadyButtonClick(manager: FragmentManager)
        fun savePreferences(name: String, age: String, weight: String)
    }
}