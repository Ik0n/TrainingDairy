package ru.ikon.trainingdairy.ui.day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.App
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.databinding.FragmentDayBinding
import ru.ikon.trainingdairy.domain.model.DiaryEntryModel
import ru.ikon.trainingdairy.ui.day.recycler.EntryCardAdapter
import ru.ikon.trainingdairy.ui.day.recycler.OnMeasureClickListener
import ru.ikon.trainingdairy.ui.day.recycler.OnNoteClickListener
import ru.ikon.trainingdairy.ui.measure.MeasureFragment
import ru.ikon.trainingdairy.ui.day.recycler.OnItemClickListener
import ru.ikon.trainingdairy.ui.note.NoteDialogFragment
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

private const val DATE = "date"

class DayFragment : Fragment(), DayContract.View, OnNoteClickListener, OnItemClickListener, OnMeasureClickListener {

    private lateinit var presenter: DayContract.Presenter

    private var _binding: FragmentDayBinding? = null
    private val binding: FragmentDayBinding get() { return _binding!! }

    private val adapter = EntryCardAdapter()

    private lateinit var date : Date

    companion object {
        @JvmStatic
        fun newInstance(date: Date) : Fragment {
            return DayFragment().apply {

                // Добавляем в аргументы фрагмента дату. Правда, в Bundle нельзя
                // положить Date, поэтому предварительно вытаскиваем из даты
                // миллисекунды в формате Long и засовываем в аргументы их
                arguments = Bundle().apply {
                    putLong(DATE, date.time)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // При создании фрагмента извлекаем миллисекунды
        // из Bundle и снова преобразуем их к типу Date.
        // Полученное значение помещаем в поле date
        arguments?.let {
            val milliseconds = it.getLong(DATE)
            date = Date(milliseconds)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        presenter = DayPresenter()
        presenter.attach(this)

        _binding = FragmentDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализируем меню из плавающих кнопок действия и сами эти кнопки
        initializeFloatingActionButtons()

        // Вызываем у презентера метод onCreate, передавая туда дату, чтобы он
        // запросил из репозитория список записей за эту дату
        presenter.onCreate(date)

        initializeControlButtons()
        adapter.setOnNoteClickListener(this)
        adapter.setOnMeasureClickListener(this)

        (activity as AppCompatActivity).supportActionBar?.show()

        (activity as AppCompatActivity).supportActionBar?.title = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(date)

    }

    /**
     * Инициализирует плавающие кнопки действия и устанавливает им обработчики нажатия
     */
    private fun initializeFloatingActionButtons() {
        // Устанавливаем обработчики нажатия плавающим кнопкам действия
        with(binding) {
            noteButton.setOnClickListener {
                floatingActionMenu.close(true)

                NoteDialogFragment().show(
                    childFragmentManager, NoteDialogFragment.TAG
                )
            }

            trainingButton.setOnClickListener {
                floatingActionMenu.close(true)

                val trainingFragment = TrainingFragment.newInstance(0)
                startFragment(trainingFragment)
            }
        }
    }

    private fun startFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.animator.fragment_fade_in, R.animator.fragment_fade_out)
            .addToBackStack("")
            .replace(R.id.fragment_holder, fragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun showData(data: List<DiaryEntryModel>) {
        // Презентер вернул данные, отображаем их с помошью адаптера
        binding.recyclerView.adapter = adapter.apply {
            setData(data)
        }
    }

    private fun initializeControlButtons() {
        with(binding) {
            floatingActionItem3.setOnClickListener {
                NoteDialogFragment().show(
                    childFragmentManager, NoteDialogFragment.TAG
                )
            }
            floatingActionItem2.setOnClickListener {
                startFragment(MeasureFragment.newInstance())
            }
    override fun onItemClick(item: DiaryEntryModel) {
        if (item is NoteModel) {
            NoteDialogFragment(item.id).show(
                childFragmentManager, NoteDialogFragment.TAG
            )
        }

        else if (item is TrainingModel) {
            val trainingFragment = TrainingFragment.newInstance(item.id)
            startFragment(trainingFragment)
        }
    }

    override fun onNoteClick(id: Long) {
        NoteDialogFragment(id).show(
            childFragmentManager, NoteDialogFragment.TAG
        )
    }

    private fun startFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.animator.fragment_fade_in, R.animator.fragment_fade_out)
            .addToBackStack("")
            .replace(R.id.fragment_holder, fragment)
            .commit()
    }

    override fun onMeasureClick() {
        startFragment(MeasureFragment.newInstance())
    }
}