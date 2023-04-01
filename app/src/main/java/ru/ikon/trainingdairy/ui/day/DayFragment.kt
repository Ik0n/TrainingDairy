package ru.ikon.trainingdairy.ui.day

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.app
import ru.ikon.trainingdairy.databinding.FragmentDayBinding
import ru.ikon.trainingdairy.domain.model.*
import ru.ikon.trainingdairy.ui.day.recycler.EntryCardAdapter
import ru.ikon.trainingdairy.ui.day.recycler.OnDeleteButtonClickListener
import ru.ikon.trainingdairy.ui.day.recycler.OnItemClickListener
import ru.ikon.trainingdairy.ui.measure.MeasureFragment
import ru.ikon.trainingdairy.ui.note.NoteDialogFragment
import ru.ikon.trainingdairy.ui.note.OnOkButtonClickListener
import ru.ikon.trainingdairy.ui.training.TrainingFragment
import ru.ikon.trainingdairy.ui.training.recycler.ExerciseTrainingAdapter
import ru.ikon.trainingdairy.utils.DATE
import ru.ikon.trainingdairy.utils.NOTE_DIALOG_FRAGMENT_TAG
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class DayFragment : Fragment(), DayContract.View, OnItemClickListener, OnOkButtonClickListener,
    OnDeleteButtonClickListener {

    @Inject
    lateinit var presenter: DayContract.Presenter

    private var _binding: FragmentDayBinding? = null
    private val binding: FragmentDayBinding
        get() {
            return _binding!!
        }

    private lateinit var adapter: EntryCardAdapter

    private lateinit var date: Date

    companion object {
        @JvmStatic
        fun newInstance(date: Date): Fragment {
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

        adapter = EntryCardAdapter(this.requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireContext().app.di.inject(this)
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

        adapter.setOnItemClickListener(this)
        adapter.setOnDeleteButtonClickListener(this)
        NoteDialogFragment.setOnOkButtonClickListener(this)

        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        (activity as AppCompatActivity).supportActionBar?.title =
            SimpleDateFormat(getString(R.string.date_format_for_actionbar_title), Locale(getString(R.string.locale_ru))).format(date)

    }

    /**
     * Инициализирует плавающие кнопки действия и устанавливает им обработчики нажатия
     */
    private fun initializeFloatingActionButtons() {
        // Устанавливаем обработчики нажатия плавающим кнопкам действия
        with(binding) {
            noteButton.setOnClickListener {
                floatingActionMenu.close(true)
                NoteDialogFragment.newInstance(date.time).show(
                    childFragmentManager, NOTE_DIALOG_FRAGMENT_TAG
                )
            }
            trainingButton.setOnClickListener {
                floatingActionMenu.close(true)

                val trainingFragment = TrainingFragment.newInstance(0, date.time)
                startFragment(trainingFragment)
            }
            measureButton.setOnClickListener {
                floatingActionMenu.close(true)
                startFragment(MeasureFragment.newInstance(0, date.time))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onCreate(date)
        adapter.notifyDataSetChanged()
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detach()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun showData(data: List<DiaryEntryModel>) {
        if (data.isEmpty()) {
            binding.emptyTitleText.visibility = View.VISIBLE
        } else {
            binding.emptyTitleText.visibility = View.GONE
        }

        // Презентер вернул данные, отображаем их с помошью адаптера
        binding.recyclerView.adapter = adapter.apply {
            setData(data)
        }
    }

    private fun startFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.fragment_holder, fragment)
            .commit()
    }

    override fun onItemClick(item: DiaryEntryModel) {
        if (item is NoteModel) {
            NoteDialogFragment.newInstance(date.time, item.id).show(
                childFragmentManager, NOTE_DIALOG_FRAGMENT_TAG
            )
        } else if (item is TrainingModel) {
            val trainingFragment = TrainingFragment.newInstance(item.id, date.time)
            startFragment(trainingFragment)
        } else if (item is MeasureModel) {
            startFragment(MeasureFragment.newInstance(item.id, date.time))
        }
    }

    override fun onOkButtonClick(date: Date) {
        presenter.onCreate(date)
        this.date = date
        adapter.notifyDataSetChanged()

        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        (activity as AppCompatActivity).supportActionBar?.title =
            SimpleDateFormat(getString(R.string.date_format_for_actionbar_title), Locale(getString(R.string.locale_ru))).format(date)
    }

    override fun onDeleteButtonClick(data: DiaryEntryModel) {
        showDeleteConfirmationDialog(data)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    /**
     * Отображает диалог подтверждения удаления для данной записи
     * @param entry Удаляемая запись
     */
    private fun showDeleteConfirmationDialog(entry: DiaryEntryModel) {
        // Создаём AlertDialog.Builder и устанавливаем сообщение и обработчики нажатий
        // для положительной и отрицательной кнопок
        val builder = AlertDialog.Builder(context)
        builder.setMessage(getString(R.string.delete_dialog_message))
        builder.setPositiveButton(
            getString(R.string.delete_dialog_positive_button)
        ) { _, _ ->
            when (entry) {
                is TrainingModel -> {
                    presenter.onTrainingDeleted(entry.id)
                }
                is MeasureModel -> {
                    presenter.onMeasureDeleted(entry.id)
                }
                is NoteModel -> {
                    presenter.onNoteDeleted(entry.id)
                }
            }
        }
        builder.setNegativeButton(
            getString(R.string.delete_dialog_negative_button)
        ) { dialog, id -> // При нажатии кнопки "Отмена" закрываем диалог
            dialog?.dismiss()
        }

        // Создаём и показываем AlertDialog
        val alertDialog = builder.create()
        alertDialog.show()
    }
}