package ru.ikon.trainingdairy.ui.note

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ru.ikon.trainingdairy.databinding.DialogFragmentNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class NoteDialogFragment(var id: Long? = null, var currentDate: Date? = null) :
    DialogFragment(), NoteContract.View {

    private var _binding: DialogFragmentNoteBinding? = null
    private val binding: DialogFragmentNoteBinding
        get() {
            return _binding!!
        }

    private lateinit var presenter: NoteContract.Presenter

    private var dateTemp = Date()

    private lateinit var listener: OnOkButtonClickListener

    /** Календарь, который будет использован для выбора даты  */
    private var mCalendar = Calendar.getInstance()

    override fun showData() {
        TODO("Not yet implemented")
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

        if (currentDate != null) {
            binding.editTextDate.setText(currentDate?.let {
                SimpleDateFormat(
                    "dd MMMM yyyy",
                    Locale.ENGLISH
                ).format(it)
            })
        }

        id?.let {
            binding.editTextDate.setText(
                SimpleDateFormat(
                    "dd MMMM yyyy",
                    Locale.ENGLISH
                ).format(presenter.getNote(it).date)
            )
            binding.editTextBody.setText(presenter.getNote(it).text)
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
                        presenter.updateNote(id, dateTemp, editTextBody.text.toString())
                    } ?: presenter.saveNote(dateTemp, editTextBody.text.toString())
                    listener.onOkButtonClick(dateTemp)
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

                    dateTemp = GregorianCalendar(year, monthOfYear, dayOfMonth).time
                }

            DatePickerDialog(
                requireContext(), dateSetListener, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    fun setOnOkButtonClickListener(listener: OnOkButtonClickListener) {
        this.listener = listener
    }

    companion object {
        const val TAG = "NoteDialogFragment"
    }
}