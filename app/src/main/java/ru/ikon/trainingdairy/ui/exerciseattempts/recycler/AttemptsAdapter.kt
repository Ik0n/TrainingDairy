package ru.ikon.trainingdairy.ui.exerciseattempts.recycler

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.databinding.CardAttemptItemBinding
import ru.ikon.trainingdairy.databinding.FragmentExerciseAttemptsBinding
import ru.ikon.trainingdairy.domain.model.AttemptModel
import ru.ikon.trainingdairy.domain.model.ExerciseModel

class AttemptsAdapter(private val context: Context) : RecyclerView.Adapter<AttemptsAdapter.ViewHolder>() {

    private val data: MutableList<AttemptModel> = mutableListOf()

    private lateinit var onItemClickListener: OnItemClickListener
    private lateinit var onDeleteButtonClickListener: OnDeleteButtonClickListener

    fun setData(data: List<AttemptModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttemptsAdapter.ViewHolder {
        return ViewHolder(CardAttemptItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AttemptsAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(private val binding: CardAttemptItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(attempt: AttemptModel) {
            with(binding) {
                textViewValue1.text = attempt.weight.toString()
                textViewValue2.text = attempt.count.toString()

                root.setOnClickListener {
                    onItemClickListener.onItemClick(attempt)
                }
                // Получаем из макета кнопку с тремя точками и настраиваем контекстное меню
                buttonMenu.setOnClickListener {
                    val popupMenu = PopupMenu(context, it)
                    popupMenu.menuInflater.inflate(
                        R.menu.menu_exercise_popup,
                        popupMenu.menu
                    )
                    popupMenu.setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.action_history -> {
                                //При выборе пункта меню "История" запускаем операцию HistoryActivity,
                                // предварительно передав название упражнения
                                // TODO: Реализовать вызов фрагмента HistoryFragment
                                true
                            }
                            R.id.action_edit -> {
                                // При выборе пункта меню "Редактировать"
                                // запускаем операцию TrainingActivity,
                                // предварительно передав ID записи, по которой щёлкнули
                                onItemClickListener.onItemClick(attempt)
                                true
                            }
                            R.id.action_delete -> {
                                // При выборе пункта меню "Удалить"
                                // отображаем диалог подтверждения удаления
                                onDeleteButtonClickListener.onDeleteButtonClick(attempt)
                                true
                            }
                            else -> false
                        }
                    }
                    popupMenu.show()
                }
            }
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    fun setOnDeleteButtonClickListener(listener: OnDeleteButtonClickListener) {
        this.onDeleteButtonClickListener = listener
    }

}