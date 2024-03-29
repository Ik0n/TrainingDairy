package ru.ikon.trainingdairy.ui.training.recycler

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
import ru.ikon.trainingdairy.domain.model.AttemptModel
import ru.ikon.trainingdairy.domain.model.ExerciseModel
import java.lang.String
import kotlin.Int

class ExerciseTrainingAdapter(private val context: Context) : RecyclerView.Adapter<ExerciseTrainingAdapter.ViewHolder>() {

    private val data : MutableList<ExerciseModel> = mutableListOf()

    private lateinit var onDeleteButtonClickListener: OnDeleteButtonClickListener
    private lateinit var onHistoryButtonClickListener: OnHistoryButtonClickListener
    private lateinit var onItemClickListener: OnItemClickListener

    fun setData(data: List<ExerciseModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(CardExerciseLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(private val binding: CardExerciseLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: ExerciseModel) {
            // Получаем из макета элемент TextView и устанавливаем ему текст - название упражнения
            binding.textViewName.text = exercise.name

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

            // Получаем из макета кнопку с тремя точками и настраиваем контекстное меню
            binding.buttonMenu.setOnClickListener {
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
                            onHistoryButtonClickListener.onHistoryButtonClick(exercise)
                            true
                        }
                        R.id.action_edit -> {
                            // При выборе пункта меню "Редактировать"
                            // запускаем операцию TrainingActivity,
                            // предварительно передав ID записи, по которой щёлкнули
                            onItemClickListener.onItemClick(exercise)
                            true
                        }
                        R.id.action_delete -> {
                            // При выборе пункта меню "Удалить"
                            // отображаем диалог подтверждения удаления
                            onDeleteButtonClickListener.onDeleteButtonClick(exercise)
                            true
                        }
                        else -> false
                    }
                }
                popupMenu.show()
            }

            // Устанавливаем обработчик нажатия на rootView, то есть, на саму карточку
            binding.root.setOnClickListener {
                // Запускаем операцию ExerciseActivity,
                // предварительно передав в неё имя упражнения для отображения в заголовке
                // и список подходов
                onItemClickListener.onItemClick(exercise)
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

            // Программно добавляем первый столбец таблицы, в котором указаны наименования строк.
            val measureHeadingTextView = TextView(context)
            measureHeadingTextView.text = context.getString(R.string.training_fragment_recycler_measure_heading)
            measureHeadingTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12f)
            measureHeadingTextView.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
            measureHeadingTextView.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.secondary_text
                )
            )
            measureHeadingTextView.setTypeface(Typeface.SANS_SERIF)
            measureHeadingTextView.gravity = Gravity.CENTER_VERTICAL
            val params1 = TableRow.LayoutParams()
            params1.setMargins(
                convertDpToPx(16),
                convertDpToPx(4),
                convertDpToPx(8),
                convertDpToPx(4)
            )
            measureHeadingTextView.layoutParams = params1

            // Добавляем содержимое в строку с весом
            rowTitle.addView(measureHeadingTextView)


            val weightHeadingTextView = TextView(context)
            weightHeadingTextView.text = context.getString(R.string.training_fragment_recycler_weight_heading)
            weightHeadingTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f)
            weightHeadingTextView.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
            weightHeadingTextView.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.primary_text
                )
            )
            weightHeadingTextView.setTypeface(Typeface.SANS_SERIF)
            weightHeadingTextView.gravity = Gravity.CENTER_VERTICAL
            val params2 = TableRow.LayoutParams()
            params2.setMargins(
                convertDpToPx(16),
                convertDpToPx(4),
                convertDpToPx(8),
                convertDpToPx(4)
            )
            weightHeadingTextView.layoutParams = params2

            // Добавляем содержимое в строку с весом
            rowWeight.addView(weightHeadingTextView)


            val countHeadingTextView = TextView(context)
            countHeadingTextView.text = context.getString(R.string.training_fragment_recycler_count_heading)
            countHeadingTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f)
            countHeadingTextView.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
            countHeadingTextView.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.primary_text
                )
            )
            countHeadingTextView.setTypeface(Typeface.SANS_SERIF)
            countHeadingTextView.gravity = Gravity.CENTER_VERTICAL
            val params3 = TableRow.LayoutParams()
            params3.setMargins(
                convertDpToPx(16),
                convertDpToPx(4),
                convertDpToPx(8),
                convertDpToPx(4)
            )
            countHeadingTextView.layoutParams = params3

            // Добавляем содержимое в строку с количеством повторений
            rowCount.addView(countHeadingTextView)


            // Перебираем коллекцию подходов данного упражнения
            for (i in 0 until exercise.attemptsList.size) {
                val attempt: AttemptModel = exercise.attemptsList.get(i)

                // Создаём специальный рамочный макет для хранения номера подхода
                // Это необходимо для коректного отображения кружка,
                // внутри которого находится номер подхода.
                // Устанавливаем ему параметры отображения
                val frameLayout = FrameLayout(context)
                val params0 = TableRow.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1F
                )
                params0.gravity = Gravity.CENTER
                frameLayout.layoutParams = params0

                // Инициализируем текстовую надпись, в которой будет отображаться номер подхода.
                // Устанавливаем ей параметры отображения.
                val indexTextView = TextView(context)
                indexTextView.text = (i + 1).toString()
                indexTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12f)
                indexTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
                indexTextView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        android.R.color.white
                    )
                )
                indexTextView.setTypeface(Typeface.SANS_SERIF)
                indexTextView.gravity = Gravity.CENTER
                indexTextView.background =
                    context.getDrawable(R.drawable.circle_orange_small)
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
                val weightTextView = TextView(context)
                weightTextView.text = String.valueOf(attempt.weight)
                weightTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f)
                weightTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
                weightTextView.setTextColor(
                    ContextCompat.getColor(
                        context,
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
                val countTextView = TextView(context)
                countTextView.text = String.valueOf(attempt.count)
                countTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f)
                countTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
                countTextView.setTextColor(
                    ContextCompat.getColor(
                        context,
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
            val scale = context.resources.displayMetrics.density
            return (dp * scale + 0.5f).toInt()
        }


    }

    fun setOnDeleteButtonClickListener(listener: OnDeleteButtonClickListener) {
        this.onDeleteButtonClickListener = listener
    }

    fun setOnHistoryButtonClickListener(listener: OnHistoryButtonClickListener) {
        this.onHistoryButtonClickListener = listener
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }
}