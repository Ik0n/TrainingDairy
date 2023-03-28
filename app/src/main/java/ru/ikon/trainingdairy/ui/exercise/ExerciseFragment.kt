package ru.ikon.trainingdairy.ui.exercise

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.app
import ru.ikon.trainingdairy.databinding.FragmentExerciseBinding
import ru.ikon.trainingdairy.domain.model.ExerciseModel
import ru.ikon.trainingdairy.ui.MainActivity
import ru.ikon.trainingdairy.ui.exercise.recycler.ExerciseAdapter
import ru.ikon.trainingdairy.ui.exercise.recycler.OnHistoryButtonClickListener
import ru.ikon.trainingdairy.ui.history.HistoryFragment
import ru.ikon.trainingdairy.utils.TRAINING_ID
import javax.inject.Inject
import kotlin.collections.ArrayList

class ExerciseFragment : Fragment(), ExerciseContract.View,
    OnHistoryButtonClickListener {

    @Inject
    lateinit var presenter: ExerciseContract.Presenter

    private var _binding: FragmentExerciseBinding? = null
    private val binding: FragmentExerciseBinding get() { return _binding!! }

    private val adapter = ExerciseAdapter()

    private var trainingId: Long = 0

    companion object {

        @JvmStatic
        fun newInstance(trainingId: Long): Fragment {
            return ExerciseFragment().apply {
                arguments = Bundle().apply {
                    putLong(TRAINING_ID, trainingId)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            trainingId = it.getLong(TRAINING_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        requireContext().app.di.inject(this)
        presenter.attach(this)

        _binding = FragmentExerciseBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onCreate(trainingId)

        adapter.setOnHistoryButtonClickListener(this@ExerciseFragment)

        initializeActionBar()
    }

    override fun showData(data: List<ExerciseModel>) {
        binding.exerciseListView.adapter = adapter.apply {
            setData(data)
        }
    }

    private fun initializeActionBar() {
        // Поскольку этот фрагмент имеет свой собственный Toolbar с тремя полями,
        // при открытии этого фрагмента мы обращаемся к Activity и скрываем у неё основной Toolbar
        (activity as MainActivity).hideActionBar()

        // Устанавливаем наш кастомный Toolbar в качестве SupportActionBar,
        // чтобы отобразить на нём кнопки Назад и Сохранить
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).setTitle(getString(R.string.exercise_fragment_actionbar_title))

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
            presenter.saveExercises(trainingId, adapter.getData() as ArrayList<ExerciseModel>)
            (activity as AppCompatActivity).supportFragmentManager.popBackStack()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun startFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.fragment_holder, fragment)
            .commit()
    }

    override fun onHistoryButtonClick(data: ExerciseModel) {
        startFragment(HistoryFragment.newInstance(data.name.toString()))
    }
}