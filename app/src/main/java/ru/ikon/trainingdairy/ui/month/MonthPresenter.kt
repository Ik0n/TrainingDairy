package ru.ikon.trainingdairy.ui.month

class MonthPresenter : MonthContract.Presenter {
    private var view: MonthContract.View? = null

    override fun attach(view: MonthContract.View) {
        this.view = view
    }

    override fun onCreate() {
        // Этот метод у презентера вызывает фрагмент (View) после того, как он создан.
        // Здесь будут инициализированы данные, а затем презентер передаст их во вью,
        // вызвав у него метод showData. Пока что мы передаём туда простую строку, которая
        // будет показана в виде Toast-сообщения
        view?.showData("Презентер на связи!")
    }

    override fun detach() {
        view = null
    }
}