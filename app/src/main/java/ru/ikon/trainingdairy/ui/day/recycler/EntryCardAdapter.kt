package ru.ikon.trainingdairy.ui.day.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.ikon.trainingdairy.databinding.CardMeasureItemBinding
import ru.ikon.trainingdairy.databinding.CardNoteItemBinding
import ru.ikon.trainingdairy.databinding.CardTrainingItemBinding
import ru.ikon.trainingdairy.domain.model.*
import java.text.SimpleDateFormat

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

    inner class MeasureViewHolder(private val binding: CardMeasureItemBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: DiaryEntryModel) {
            val measure = data as MeasureModel

            var headingString = ""

            if (measure.parametersList.size == 0) {
                headingString = "Нет значений"
            }

            for (i in 0 until measure.parametersList.size) {
                val parameterModel: ParameterModel = measure.parametersList.get(i)
                val name: String = parameterModel.name
                val valueString: String =
                    java.lang.String.valueOf(parameterModel.value)
                headingString += "$name: $valueString. "
            }

            binding.textViewMeasureHeading.text = headingString

            val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
            val dateString = simpleDateFormat.format(measure.date)
            binding.textViewMeasureSubheading.text = dateString

            binding.cardView.setOnClickListener {
                this@EntryCardAdapter.listener.onItemClick(data)
            }
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

    inner class TrainingViewHolder(private val binding: CardTrainingItemBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: DiaryEntryModel) {
            val training = (data as TrainingModel)
            binding.textViewMeasureHeading.text = training.name

            val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
            val dateString = simpleDateFormat.format(training.date)
            binding.textViewSubheading.text = dateString

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
                NoteViewHolder(CardNoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }


}

