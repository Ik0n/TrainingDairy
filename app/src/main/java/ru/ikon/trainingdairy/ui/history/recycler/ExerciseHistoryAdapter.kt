package ru.ikon.trainingdairy.ui.history.recycler

import android.content.Context
import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.databinding.CardExerciseLayoutBinding
import ru.ikon.trainingdairy.databinding.ExerciseHistoryLayoutBinding
import ru.ikon.trainingdairy.domain.model.AttemptModel
import ru.ikon.trainingdairy.domain.model.ExerciseModel
import java.lang.String
import java.text.SimpleDateFormat
import java.util.*
import kotlin.Int

class ExerciseHistoryAdapter(private val mContext: Context) : RecyclerView.Adapter<ExerciseHistoryAdapter.ViewHolder>() {

    private val data : MutableList<ExerciseModel> = mutableListOf()

    fun setData(data: List<ExerciseModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(ExerciseHistoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(private val binding: ExerciseHistoryLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: ExerciseModel) {
            // Получаем из макета элемент TextView и устанавливаем ему текст - название упражнения

            binding.textViewName.text = SimpleDateFormat("dd MMMM yyyy", Locale("ru")).format(exercise.date)

            // Инициализируем таблицу подходов и заполняем её данными
            val table = binding.tableLayout
            fillTableLayout(table, exercise)

            // Устанавливаем обработчик нажатия на кнопку сворачивания/разворачивания
            // При нажатии на эту кнопку меняем видимость таблицы и внешний вид кнопки
            binding.buttonExpand.setOnClickListener {
                if (table.visibility == View.GONE) {
                    table.visibility = View.VISIBLE
                    binding.buttonExpand.setImageResource(R.drawable.ic_expand_less_black_24dp)
                } else if (table.visibility == View.VISIBLE) {
                    table.visibility = View.GONE
                    binding.buttonExpand.setImageResource(R.drawable.ic_expand_more_black_24dp)
                }
            }

            // Если список подходов данного упражнения пуст, также скрываем таблицу
            if (exercise.attemptsList.size == 0) {
                table.visibility = View.GONE
                binding.buttonExpand.setImageResource(R.drawable.ic_expand_more_black_24dp)
                binding.buttonExpand.isEnabled = false
            }
        }

        /**
         * Заполняет таблицу подходов данными об упражнении
         * @param table Таблица подходов
         * @param exercise Упражнение
         */
        private fun fillTableLayout(
            table: TableLayout,
            exercise: ExerciseModel
        ) {
            // Добавляем в таблицу строки
            val rowTitle = binding.rowTitle
            val rowWeight = binding.rowWeight
            val rowCount = binding.rowCount

            // Перебираем коллекцию подходов данного упражнения
            for (i in 0 until exercise.attemptsList.size) {
                val attempt: AttemptModel = exercise.attemptsList.get(i)

                // Создаём специальный рамочный макет для хранения номера подхода
                // Это необходимо для коректного отображения кружка,
                // внутри которого находится номер подхода.
                // Устанавливаем ему параметры отображения
                val frameLayout = FrameLayout(mContext)
                val params0 = TableRow.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1F
                )
                params0.gravity = Gravity.CENTER
                frameLayout.layoutParams = params0

                // Инициализируем текстовую надпись, в которой будет отображаться номер подхода.
                // Устанавливаем ей параметры отображения.
                val indexTextView = TextView(mContext)
                indexTextView.text = (i + 1).toString()
                indexTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12f)
                indexTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
                indexTextView.setTextColor(
                    ContextCompat.getColor(
                        mContext,
                        android.R.color.white
                    )
                )
                indexTextView.setTypeface(Typeface.SANS_SERIF)
                indexTextView.gravity = Gravity.CENTER
                indexTextView.background =
                    mContext.getDrawable(R.drawable.circle_orange_small)
                val params1 = TableRow.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                params1.setMargins(
                    convertDpToPx(8),
                    convertDpToPx(4),
                    convertDpToPx(8),
                    convertDpToPx(4)
                )

                // Добавляем содержимое в строку с номерами подходов
                indexTextView.layoutParams = params1
                frameLayout.addView(indexTextView)
                rowTitle.addView(frameLayout)

                // Инициализируем текстовую надпись, в которой будет отображаться вес.
                // Устанавливаем ей параметры отображения.
                val weightTextView = TextView(mContext)
                weightTextView.text = String.valueOf(attempt.weight)
                weightTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f)
                weightTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
                weightTextView.setTextColor(
                    ContextCompat.getColor(
                        mContext,
                        R.color.primary_text
                    )
                )
                weightTextView.setTypeface(Typeface.SANS_SERIF)
                weightTextView.gravity = Gravity.CENTER_VERTICAL
                val params2 = TableRow.LayoutParams()
                params2.setMargins(
                    convertDpToPx(8),
                    convertDpToPx(4),
                    convertDpToPx(8),
                    convertDpToPx(4)
                )
                weightTextView.layoutParams = params2

                // Добавляем содержимое в строку с весом
                rowWeight.addView(weightTextView)

                // Инициализируем текстовую надпись, в которой будет отображаться количество повторений.
                // Устанавливаем ей параметры отображения.
                val countTextView = TextView(mContext)
                countTextView.text = String.valueOf(attempt.count)
                countTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f)
                countTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
                countTextView.setTextColor(
                    ContextCompat.getColor(
                        mContext,
                        R.color.primary_text
                    )
                )
                countTextView.setTypeface(Typeface.SANS_SERIF)
                countTextView.gravity = Gravity.CENTER_VERTICAL
                val params3 = TableRow.LayoutParams()
                params3.setMargins(
                    convertDpToPx(8),
                    convertDpToPx(4),
                    convertDpToPx(8),
                    convertDpToPx(4)
                )
                countTextView.layoutParams = params3

                // Добавляем содержимое в строку с количеством повторений
                rowCount.addView(countTextView)
            }
        }

        /**
         * Преобразует не зависящие от плотности пиксели (dp) в пиксели (px)
         * @param dp Значение в не зависящих от плотности пикселях
         * @return Значение в пикселях
         */
        private fun convertDpToPx(dp: Int): Int {
            val scale = mContext.resources.displayMetrics.density
            return (dp * scale + 0.5f).toInt()
        }
    }
}