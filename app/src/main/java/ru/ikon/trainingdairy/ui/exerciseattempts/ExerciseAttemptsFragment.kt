package ru.ikon.trainingdairy.ui.exerciseattempts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.databinding.FragmentExerciseAttemptsBinding
import ru.ikon.trainingdairy.domain.model.AttemptModel
import ru.ikon.trainingdairy.ui.MainActivity
import ru.ikon.trainingdairy.ui.attempt.AttemptFragment
import ru.ikon.trainingdairy.ui.exerciseattempts.recycler.AttemptsAdapter
import ru.ikon.trainingdairy.ui.exerciseattempts.recycler.OnDeleteButtonClickListener
import ru.ikon.trainingdairy.ui.exerciseattempts.recycler.OnItemClickListener

class ExerciseAttemptsFragment : Fragment(), ExerciseAttemptsContract.View, OnItemClickListener, OnDeleteButtonClickListener {

    private lateinit var presenter: ExerciseAttemptsContract.Presenter

    private var _binding: FragmentExerciseAttemptsBinding? = null
    private val binding: FragmentExerciseAttemptsBinding get() { return _binding!! }

    private lateinit var adapter: AttemptsAdapter

    override fun showData(data: List<AttemptModel>) {
        if (!data.isEmpty()) {
            binding.emptyTitleText.visibility = View.GONE
        }
        binding.listViewAttempts.adapter = adapter.apply {
            setData(data)
        }
    }

    private var trainingId: Long = 0
    private var exerciseId: Long = 0

    companion object {
        private const val TRAINING_ID = "training_id"
        private const val EXERCISE_ID = "exercise_id"

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
        presenter = ExerciseAttemptsPresenter()
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

    override fun onResume() {
        super.onResume()
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detach()
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
        presenter.deleteAttempt(trainingId, exerciseId, attempt.id)
    }
}