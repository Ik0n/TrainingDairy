package ru.ikon.trainingdairy.ui.measure.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.ikon.trainingdairy.databinding.CardMeasureParameterItemBinding
import ru.ikon.trainingdairy.domain.model.ParameterModel

class ParameterAdapter : RecyclerView.Adapter<ParameterAdapter.ViewHolder>() {

    private val data : MutableList<ParameterModel> = mutableListOf()

    private lateinit var listener: OnDeleteButtonClickListener

    fun setData(data: List<ParameterModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CardMeasureParameterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    inner class ViewHolder(private val binding: CardMeasureParameterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ParameterModel) {
            with(binding) {
                textViewMeasureHeading.text = data.name
                textViewMeasureSubheading.text = data.value.toString()
                buttonDelete.setOnClickListener {
                    listener.onDeleteButtonClick(data)
                }
            }
        }
    }

    fun setOnDeleteButtonClickListener(listener: OnDeleteButtonClickListener) {
        this.listener = listener
    }
}