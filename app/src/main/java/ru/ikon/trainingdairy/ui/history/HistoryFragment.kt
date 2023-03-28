package ru.ikon.trainingdairy.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import ru.ikon.trainingdairy.app
import ru.ikon.trainingdairy.databinding.FragmentHistoryBinding
import ru.ikon.trainingdairy.domain.model.ExerciseModel
import ru.ikon.trainingdairy.ui.MainActivity
import ru.ikon.trainingdairy.ui.history.recycler.ExerciseHistoryAdapter
import ru.ikon.trainingdairy.utils.ARG_NAME
import javax.inject.Inject


class HistoryFragment : Fragment(), HistoryContract.View {
    private var exerciseName: String = ""

    @Inject
    lateinit var presenter: HistoryContract.Presenter

    private var _binding: FragmentHistoryBinding? = null
    private val binding: FragmentHistoryBinding get() { return _binding!! }

    private lateinit var adapter: ExerciseHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            exerciseName = it.getString(ARG_NAME, "")
        }
        adapter = ExerciseHistoryAdapter(this.requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireContext().app.di.inject(this)
        presenter.attach(this)

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeActionBar()

        presenter.onCreate(exerciseName)
    }

    private fun initializeActionBar() {
        // Поскольку этот фрагмент имеет свой собственный Toolbar,
        // при открытии этого фрагмента мы обращаемся к Activity и скрываем у неё основной Toolbar
        (activity as MainActivity).hideActionBar()

        // Устанавливаем наш кастомный Toolbar в качестве SupportActionBar,
        // чтобы отобразить на нём кнопки Назад и Сохранить
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).setTitle(exerciseName)

        // Для отображения системной кнопки Назад
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        // Для отображения меню (которое в нашем случае состоит только из одного пункта - сохранить)
        setHasOptionsMenu(true)
    }

    companion object {
        @JvmStatic
        fun newInstance(name: String) =
            HistoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, name)
                }
            }
    }

    override fun showExercises(exerciseList: List<ExerciseModel>) {
        if (exerciseList.isEmpty()) {
            binding.emptyTitleText.visibility = View.VISIBLE
        } else {
            binding.emptyTitleText.visibility = View.GONE
        }
        binding.recyclerViewExercises.adapter = adapter.apply {
            setData(exerciseList)
        }
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}