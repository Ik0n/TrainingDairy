package ru.ikon.trainingdairy.ui.exercise.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.ikon.trainingdairy.databinding.CardExerciseCheckboxBinding
import ru.ikon.trainingdairy.domain.model.ExerciseModel
import ru.ikon.trainingdairy.ui.training.recycler.OnHistoryButtonClickListener

class ExerciseAdapter : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    private val data : MutableList<ExerciseModel> = mutableListOf()

    private lateinit var onHistoryButtonClickListener: OnHistoryButtonClickListener

    fun setData(data: List<ExerciseModel>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun getData() = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseAdapter.ViewHolder {
        return ViewHolder(CardExerciseCheckboxBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ExerciseAdapter.ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    inner class ViewHolder(private val binding: CardExerciseCheckboxBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ExerciseModel, position: Int) {
            with(binding) {
                checkbox.text = data.name
                checkbox.isChecked = data.isChecked
                checkbox.setOnCheckedChangeListener { compoundButton, b ->
                    this@ExerciseAdapter.data[position].isChecked = b
                }

                buttonHistory.setOnClickListener {
                    onHistoryButtonClickListener.onHistoryButtonClick(data)
                }
            }
        }
    }

    fun setOnHistoryButtonClickListener(listener: OnHistoryButtonClickListener) {
        this.onHistoryButtonClickListener = listener
    }
}