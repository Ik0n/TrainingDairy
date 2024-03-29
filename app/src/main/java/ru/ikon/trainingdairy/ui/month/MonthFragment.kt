package ru.ikon.trainingdairy.ui.month

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.sundeepk.compactcalendarview.CompactCalendarView.CompactCalendarViewListener
import com.github.sundeepk.compactcalendarview.domain.Event
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.app
import ru.ikon.trainingdairy.databinding.FragmentMonthBinding
import ru.ikon.trainingdairy.domain.model.DiaryEntryModel
import ru.ikon.trainingdairy.domain.model.MeasureModel
import ru.ikon.trainingdairy.domain.model.NoteModel
import ru.ikon.trainingdairy.domain.model.TrainingModel
import ru.ikon.trainingdairy.ui.day.DayFragment
import ru.ikon.trainingdairy.ui.measure.MeasureFragment
import ru.ikon.trainingdairy.ui.note.NoteDialogFragment
import ru.ikon.trainingdairy.ui.note.OnOkButtonClickListener
import ru.ikon.trainingdairy.ui.training.TrainingFragment
import ru.ikon.trainingdairy.utils.NOTE_DIALOG_FRAGMENT_TAG
import java.util.*
import javax.inject.Inject

class MonthFragment : Fragment(), MonthContract.View, OnOkButtonClickListener {

    @Inject
    lateinit var presenter: MonthContract.Presenter

    private var _binding: FragmentMonthBinding? = null
    private val binding: FragmentMonthBinding get() { return _binding!! }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireContext().app.di.inject(this)
        presenter.attach(this)

        _binding = FragmentMonthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализируем элемент CompactCalendarView. Этот метод оставляем здесь,
        // так как происходит инициализация конкретного элемента на макете, и она
        // никак не связана с данными
        initializeCalendar()

        // Инициализируем меню из плавающих кнопок действия и сами эти кнопки
        initializeFloatingActionButtons()

        NoteDialogFragment.setOnOkButtonClickListener(this)

        (activity as AppCompatActivity).supportActionBar?.show()

        if ((activity as AppCompatActivity).supportActionBar?.title != getString(R.string.app_name))
            (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        // Передаём в Presenter сообщение о том, что фрагмент создан, чтобы получить данные
        presenter.onCreate()
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() : Fragment {
            return MonthFragment()
        }
    }

    /** Инициализирует элемент compactCalendarView на макете  */
    private fun initializeCalendar() {
        // Инициализируем календарь
        binding.compactCalendarView.setUseThreeLetterAbbreviation(true)

        // Получаем первый день текущего месяца (для определения месяца)
        val firstDayOfMonth: Date = binding.compactCalendarView.firstDayOfCurrentMonth
        setHeading(firstDayOfMonth)

        // Добавляем обработчики событий выбора дня и прокрутки месяца
        binding.compactCalendarView.setListener(object : CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {
                // При нажатии на конкретный день открываем фрагмент DayFragment,
                // передавая туда кликнутую дату (безо всякого формата). DayFragment
                // при открытии сам запросит из репозитория данные на эту дату.
                val dayFragment = DayFragment.newInstance(dateClicked)

                startFragment(dayFragment)
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date) {
                // При прокрутке на другой месяц меняем заголовок календаря
                setHeading(firstDayOfNewMonth)
            }
        })

        // Инициализируем кнопки для перехода к предыдущему/следующему месяцам
        // и устанавливаем им обработчики нажатия
        binding.buttonPreviousMonth.setOnClickListener { binding.compactCalendarView.scrollLeft() }
        binding.buttonNextMonth.setOnClickListener { binding.compactCalendarView.scrollRight() }
    }

    /**
     * Устанавливает заголовок календаря в формате "Май 2023"
     * @param firstDayOfMonth Дата, соответствующая первому дню месяца, для определения текущего месяца
     * @return Заголовок для календаря в строковой форме
     */
    private fun setHeading(firstDayOfMonth: Date) {
        // Инициализируем строку-результат
        var resultString = String()

        // Определяем индекс месяца (от 0 до 11)
        resultString = resources.getStringArray(R.array.months)[firstDayOfMonth.month]

        // Добавляем пробел между месяцем и годом
        resultString = "$resultString "

        // Определяем год
        // Метод getYear возвращает годы с 1900 года, поэтому, чтобы получить 2023 год,
        // к полученному числу нужно прибавить 1900
        val year = firstDayOfMonth.year + 1900

        // Добавляем год к строке-результату
        resultString += year

        // Устанавливаем строку-результат в качестве заголовка календаря
        binding.textViewMonth.text = resultString
    }

    /**
     * Инициализирует плавающие кнопки действия и устанавливает им обработчики нажатия
     */
    private fun initializeFloatingActionButtons() {
        // Устанавливаем обработчики нажатия плавающим кнопкам действия
        with(binding) {
            val currentDate = Date()

            trainingButton.setOnClickListener {
                floatingActionMenu.close(true)

                val trainingFragment = TrainingFragment.newInstance(0, currentDate.time)
                startFragment(trainingFragment)
            }
            noteButton.setOnClickListener {
                floatingActionMenu.close(true)

                NoteDialogFragment.newInstance(currentDate.time).show(
                    childFragmentManager, NOTE_DIALOG_FRAGMENT_TAG
                )
            }
            measureButton.setOnClickListener {
                floatingActionMenu.close(true)

                startFragment(MeasureFragment.newInstance(0, currentDate.time))
            }
        }
    }

    override fun showData(data: List<DiaryEntryModel>) {
        fillCalendar(data)
    }

    /**
     * Заполняет календарь на главном экране данными из списка entryList
     * @param entryList Список записей для отображения их в календаре
     */
    private fun fillCalendar(entryList: List<DiaryEntryModel>) {
        // Удаляем из календаря все существующие события
        binding.compactCalendarView.removeAllEvents()

        // Проходим циклом по всему списку mEntryList
        for (i in entryList.indices) {
            // Инициализируем запись (тренировку, измерение или заметку), с которой будем работать
            val entryModel = entryList[i]

            // Получаем дату и время этой записи в виде числи миллисекунд,
            // поскольку для вставки в календарь требуется именно такое представление
            val timeInMilliseconds: Long = entryModel.date.time

            // Создаём и инициализируем переменную color, котораи будет хранить цвет маркера
            var color = -1

            // Определяем тип записи и в зависимости от него назначаем цвет
            when (entryModel) {
                is TrainingModel -> {
                    color = resources.getColor(R.color.blue)
                }
                is MeasureModel -> {
                    color = resources.getColor(R.color.green)
                }
                is NoteModel -> {
                    color = resources.getColor(R.color.orange)
                }
            }

            // Если в переменной color изменилось значение,
            // значит, тип записи удалось определить
            if (color != -1) {
                // Добавляем в календарь новое событие с указанным цветом и временем в миллисекундах
                binding.compactCalendarView.addEvent(Event(color, timeInMilliseconds, entryModel))
            }
        }
    }

    private fun startFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.fragment_holder, fragment)
            .commit()
    }

    override fun onOkButtonClick(date: Date) {
        presenter.onCreate()
    }
}