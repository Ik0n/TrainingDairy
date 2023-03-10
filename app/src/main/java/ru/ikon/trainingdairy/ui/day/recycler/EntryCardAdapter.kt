package ru.ikon.trainingdairy.ui.day.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.ikon.trainingdairy.databinding.CardMeasureItemBinding
import ru.ikon.trainingdairy.databinding.CardNoteItemBinding
import ru.ikon.trainingdairy.databinding.CardTrainingItemBinding
import ru.ikon.trainingdairy.domain.model.DiaryEntryModel
import ru.ikon.trainingdairy.domain.model.MeasureModel
import ru.ikon.trainingdairy.domain.model.NoteModel
import ru.ikon.trainingdairy.domain.model.TrainingModel

const val TYPE_MEASURE = 0
const val TYPE_NOTE = 1
const val TYPE_TRAINING = 2

class EntryCardAdapter : RecyclerView.Adapter<EntryCardAdapter.BaseViewHolder>() {

    private val data : MutableList<DiaryEntryModel> = mutableListOf()

    private lateinit var listener: OnItemClickListener

    override fun getItemViewType(position: Int): Int {
        var temp = -1
        when (data[position]) {
            is MeasureModel -> {
                temp = TYPE_MEASURE
            }
            is NoteModel -> {
                temp = TYPE_NOTE
            }
            is  TrainingModel -> {
                temp = TYPE_TRAINING
            }
        }
        return temp
    }

    fun setData(data: List<DiaryEntryModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(data: DiaryEntryModel)
    }

    class MeasureViewHolder(private val binding: CardMeasureItemBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: DiaryEntryModel) {
            binding.textViewMeasureHeading.text = (data as MeasureModel).date.toString()
        }
    }

    inner class NoteViewHolder(private val binding: CardNoteItemBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: DiaryEntryModel) {
            binding.textViewBody.text = (data as NoteModel).text.toString()
            binding.cardView.setOnClickListener {
                this@EntryCardAdapter.listener.onItemClick(data)
            }
        }
    }

    inner class TrainingViewHolder(val binding: CardTrainingItemBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: DiaryEntryModel) {
            binding.textViewMeasureHeading.text = (data as TrainingModel).text.toString()
            binding.textViewSubheading.text = data.date.toString()

            binding.cardView.setOnClickListener {
                this@EntryCardAdapter.listener.onItemClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType) {
            TYPE_MEASURE -> {
                MeasureViewHolder(CardMeasureItemBinding.inflate(LayoutInflater.from(parent.context)))
            }
            TYPE_NOTE -> {
                NoteViewHolder(CardNoteItemBinding.inflate(LayoutInflater.from(parent.context)))
            }
            TYPE_TRAINING -> {
                TrainingViewHolder(CardTrainingItemBinding.inflate(LayoutInflater.from(parent.context)))
            }
            else -> {
                NoteViewHolder(CardNoteItemBinding.inflate(LayoutInflater.from(parent.context)))
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setOnNoteClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}

