package ru.ikon.trainingdairy.ui.attempt

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.app
import ru.ikon.trainingdairy.databinding.FragmentAttemptBinding
import ru.ikon.trainingdairy.domain.model.AttemptModel
import ru.ikon.trainingdairy.ui.MainActivity
import ru.ikon.trainingdairy.utils.ATTEMPT_ID
import ru.ikon.trainingdairy.utils.EXERCISE_ID
import ru.ikon.trainingdairy.utils.TRAINING_ID
import javax.inject.Inject

class AttemptFragment : Fragment(), AttemptContract.View {

    @Inject
    lateinit var presenter: AttemptContract.Presenter

    private var _binding: FragmentAttemptBinding? = null
    private val binding: FragmentAttemptBinding get() = _binding!!

    override fun showData(data: AttemptModel) {
        if (attemptId != 0L) {
            with(binding) {
                editTextCount.setText(data.count.toString())
                editTextWeight.setText(data.weight.toString())
            }
        }
    }

    private var trainingId: Long = 0L
    private var exerciseId: Long = 0L
    private var attemptId: Long = 0L

    companion object {

        @JvmStatic
        fun newInstance(trainingId: Long, exerciseId: Long, attemptId: Long) : Fragment {
            return AttemptFragment().apply {
                arguments = Bundle().apply {
                    putLong(TRAINING_ID, trainingId)
                    putLong(EXERCISE_ID, exerciseId)
                    putLong(ATTEMPT_ID, attemptId)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            trainingId = it.getLong(TRAINING_ID)
            exerciseId = it.getLong(EXERCISE_ID)
            attemptId = it.getLong(ATTEMPT_ID, 0L)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireContext().app.di.inject(this)
        presenter.attach(this)

        _binding = FragmentAttemptBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onCreate(trainingId, exerciseId, attemptId)

        initializeActionBar()

    }

    override fun onDetach() {
        super.onDetach()
        presenter.detach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Используем меню menu_save, в котором присутствует только один пункт - Сохранить
        inflater.inflate(R.menu.menu_save, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_save) {
            // Получаем значения веса и количества из полей ввода
            val weightString: String = binding.editTextWeight.text.toString()
            val countString: String = binding.editTextCount.text.toString()

            // Проверяем поля на заполеность.
            // Если какое-либо из них не заполнено, отображаем под ним ошибку.
            val weightLayout = binding.weightLayout
            if (weightString.isEmpty()) {
                weightLayout.error = getString(R.string.attempt_fragment_weight_layout_error)
                return false
            } else {
                weightLayout.error = null
            }

            val countLayout = binding.countLayout
            if (countString.isEmpty()) {
                countLayout.error = getString(R.string.attempt_fragment_count_layout_error)
                return false
            } else {
                countLayout.error = null
            }

            presenter.saveAttempt(
                trainingId,
                exerciseId,
                attemptId,
                binding.editTextWeight.text.toString().toInt(),
                binding.editTextCount.text.toString().toInt()
            )
            (activity as AppCompatActivity).supportFragmentManager.popBackStack()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initializeActionBar() {
        // Поскольку этот фрагмент имеет свой собственный Toolbar с тремя полями,
        // при открытии этого фрагмента мы обращаемся к Activity и скрываем у неё основной Toolbar
        (activity as MainActivity).hideActionBar()

        // Устанавливаем наш кастомный Toolbar в качестве SupportActionBar,
        // чтобы отобразить на нём кнопки Назад и Сохранить
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).setTitle(getString(R.string.attempt_fragment_actionbar_title))

        // Для отображения системной кнопки Назад
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        // Для отображения меню (которое в нашем случае состоит только из одного пункта - сохранить)
        setHasOptionsMenu(true)
    }

}