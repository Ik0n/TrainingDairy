package ru.ikon.trainingdairy.ui.day.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.ikon.trainingdairy.App
import ru.ikon.trainingdairy.databinding.CardMeasureItemBinding
import ru.ikon.trainingdairy.databinding.CardNoteItemBinding
import ru.ikon.trainingdairy.databinding.CardTrainingItemBinding
import ru.ikon.trainingdairy.domain.model.DiaryEntryModel
import ru.ikon.trainingdairy.domain.model.MeasureModel

const val TYPE_MEASURE = 0
const val TYPE_NOTE = 1
const val TYPE_TRAINING = 2

class EntryCardAdapter : RecyclerView.Adapter<EntryCardAdapter.BaseViewHolder>() {

    private val data : MutableList<Pair<DiaryEntryModel, Boolean>> = mutableListOf()

    override fun getItemViewType(position: Int): Int {
        return data[position].first.type
    }

    fun setData(data: MutableList<Pair<DiaryEntryModel, Boolean>>) {
        this.data.clear()
        this.data.addAll(data)
        println(data[0].first.type)
        notifyDataSetChanged()
    }

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(data: Pair<DiaryEntryModel, Boolean>)
    }

    class MeasureViewHolder(val binding: CardMeasureItemBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: Pair<DiaryEntryModel, Boolean>) {
            binding.textViewMeasureHeading.text = data.first.type.toString()
            binding.textViewMeasureSubheading.text = data.first.date.toString()
        }
    }

    class NoteViewHolder(val binding: CardNoteItemBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: Pair<DiaryEntryModel, Boolean>) {
            binding.textViewBody.text = data.first.type.toString()
        }
    }

    class TrainingViewHolder(val binding: CardTrainingItemBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: Pair<DiaryEntryModel, Boolean>) {
            binding.textViewMeasureHeading.text = data.first.type.toString()
            binding.textViewSubheading.text = data.first.date.toString()
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


}

