package ru.ikon.trainingdairy.ui.note

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import ru.ikon.trainingdairy.databinding.DialogFragmentNoteBinding
import ru.ikon.trainingdairy.ui.month.MonthFragment
import java.text.SimpleDateFormat
import java.util.*

class NoteDialogFragment() :
    DialogFragment(), NoteContract.View {

    private var _binding: DialogFragmentNoteBinding? = null
    private val binding: DialogFragmentNoteBinding
        get() {
            return _binding!!
        }

    private lateinit var presenter: NoteContract.Presenter

    private lateinit var currentDate : Date
    private var id : Long? = null

    /** Календарь, который будет использован для выбора даты  */
    private var mCalendar = Calendar.getInstance()

    override fun showData() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            currentDate = Date(it.getLong(DATE))
            it.getLong(ID).let {
                id = it
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        presenter = NotePresenter()
        presenter.attach(this)
        _binding = DialogFragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editTextDate.setText(
            SimpleDateFormat(
                "dd.MM.yyyy",
                Locale.ENGLISH
            ).format(currentDate)
        )




        id?.let {
            if (id != 0.toLong()) {
                binding.editTextDate.setText(
                    SimpleDateFormat(
                        "dd.MM.yyyy",
                        Locale.ENGLISH
                    ).format(presenter.getNote(it).date)
                )
                binding.editTextBody.setText(presenter.getNote(it).text)
            }
        }

        with(binding) {
            noteDateLayout.setOnClickListener {
                onDateEditTextClicked()
            }

            editTextDate.setOnClickListener {
                onDateEditTextClicked()
            }

            okButton.setOnClickListener {
                if (editTextDate.text.toString() != "" || editTextBody.text.toString() != "") {
                    id?.let { id ->
                        if (id != 0.toLong()) {
                            presenter.updateNote(id, currentDate, editTextBody.text.toString())
                        } else {
                            presenter.saveNote(currentDate, editTextBody.text.toString())
                        }
                    }
                    listener.onOkButtonClick(currentDate)
                    dismiss()
                }
            }
        }
    }

    /**
     * Обработчик нажатия на поле выбора даты
     */
    private fun onDateEditTextClicked() {
        with(binding) {
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    mCalendar[Calendar.YEAR] = year
                    mCalendar[Calendar.MONTH] = monthOfYear
                    mCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth

                    val sdf = SimpleDateFormat("dd.MM.yyyy")
                    editTextDate.setText(sdf.format(mCalendar.time))

                    currentDate = GregorianCalendar(year, monthOfYear, dayOfMonth).time
                }

            DatePickerDialog(
                requireContext(), dateSetListener,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    companion object {
        const val TAG = "NoteDialogFragment"
        const val DATE = "date"
        const val ID = "id"

        private lateinit var listener: OnOkButtonClickListener

        fun newInstance(currentDate: Date, id: Long? = null) : DialogFragment {
            return NoteDialogFragment().apply {
                arguments = Bundle().apply {
                    putLong(DATE, currentDate.time)
                    id?.let { putLong(ID, it) }
                }
            }
        }

        fun setOnOkButtonClickListener(listener: OnOkButtonClickListener) {
            this.listener = listener
        }
    }
}