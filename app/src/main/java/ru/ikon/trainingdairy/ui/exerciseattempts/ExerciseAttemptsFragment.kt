package ru.ikon.trainingdairy.ui.exerciseattempts

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.app
import ru.ikon.trainingdairy.databinding.FragmentExerciseAttemptsBinding
import ru.ikon.trainingdairy.domain.model.AttemptModel
import ru.ikon.trainingdairy.ui.MainActivity
import ru.ikon.trainingdairy.ui.attempt.AttemptFragment
import ru.ikon.trainingdairy.ui.exerciseattempts.recycler.AttemptsAdapter
import ru.ikon.trainingdairy.ui.exerciseattempts.recycler.OnDeleteButtonClickListener
import ru.ikon.trainingdairy.ui.exerciseattempts.recycler.OnItemClickListener
import ru.ikon.trainingdairy.utils.EXERCISE_ID
import ru.ikon.trainingdairy.utils.TRAINING_ID
import javax.inject.Inject

class ExerciseAttemptsFragment : Fragment(), ExerciseAttemptsContract.View, OnItemClickListener, OnDeleteButtonClickListener {

    @Inject
    lateinit var presenter: ExerciseAttemptsContract.Presenter

    private var _binding: FragmentExerciseAttemptsBinding? = null
    private val binding: FragmentExerciseAttemptsBinding get() { return _binding!! }

    private lateinit var adapter: AttemptsAdapter

    override fun showAttempts(data: List<AttemptModel>) {
        if (data.isEmpty()) {
            binding.emptyTitleText.visibility = View.VISIBLE
        } else {
            binding.emptyTitleText.visibility = View.GONE
        }
        binding.listViewAttempts.adapter = adapter.apply {
            setData(data)
        }
    }

    private var trainingId: Long = 0L
    private var exerciseId: Long = 0L

    companion object {

        @JvmStatic
        fun newInstance(trainingId: Long, exerciseId: Long): Fragment {
            return ExerciseAttemptsFragment().apply {
                arguments = Bundle().apply {
                    putLong(TRAINING_ID, trainingId)
                    putLong(EXERCISE_ID, exerciseId)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            trainingId = it.getLong(TRAINING_ID)
            exerciseId = it.getLong(EXERCISE_ID)
        }
        adapter = AttemptsAdapter(this.requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireContext().app.di.inject(this)
        presenter.attach(this)

        _binding = FragmentExerciseAttemptsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreate(trainingId, exerciseId)
        initializeActionBar()
        adapter.setOnItemClickListener(this)
        adapter.setOnDeleteButtonClickListener(this)

        binding.fab.setOnClickListener {
            startFragment(AttemptFragment.newInstance(trainingId, exerciseId, 0L))
        }
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initializeActionBar() {
        // Поскольку этот фрагмент имеет свой собственный Toolbar с тремя полями,
        // при открытии этого фрагмента мы обращаемся к Activity и скрываем у неё основной Toolbar
        (activity as MainActivity).hideActionBar()

        // Устанавливаем наш кастомный Toolbar в качестве SupportActionBar,
        // чтобы отобразить на нём кнопки Назад и Сохранить
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).setTitle(presenter.getExerciseName(trainingId, exerciseId))

        // Для отображения системной кнопки Назад
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        // Для отображения меню (которое в нашем случае состоит только из одного пункта - сохранить)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Используем меню menu_save, в котором присутствует только один пункт - Сохранить
        inflater.inflate(R.menu.menu_save, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            // При нажатии на кнопку Назад "закрываем" текущий фрагмент, удаляя его из бэк-стека
            (activity as AppCompatActivity)
                .supportFragmentManager
                .popBackStack()
        }
        if (item.itemId == R.id.action_save) {
            // TODO: Реализовать сохранение
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(attempt: AttemptModel) {
        startFragment(AttemptFragment.newInstance(trainingId,  exerciseId, attempt.id))
    }

    private fun startFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.fragment_holder, fragment)
            .commit()
    }

    override fun onDeleteButtonClick(attempt: AttemptModel) {
        showDeleteConfirmationDialog(attempt)
    }

    /**
     * Отображает диалог подтверждения удаления для данного подхода
     * @param attempt Удаляемый подход
     */
    private fun showDeleteConfirmationDialog(attempt: AttemptModel) {
        // Создаём AlertDialog.Builder и устанавливаем сообщение и обработчики нажатий
        // для положительной и отрицательной кнопок
        val builder = AlertDialog.Builder(context)
        builder.setMessage(getString(R.string.exercise_attempts_delete_dialog_message))
        builder.setPositiveButton(
            getString(R.string.delete_dialog_positive_button)
        ) { _, _ ->
            // При нажатии кнопки "Удалить" оповещаем презентер
            presenter.onAttemptDeleted(trainingId, exerciseId, attempt.id)
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