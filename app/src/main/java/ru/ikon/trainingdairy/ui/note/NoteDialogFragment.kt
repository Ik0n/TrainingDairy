package ru.ikon.trainingdairy.ui.note

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.databinding.DialogFragmentNoteBinding
import ru.ikon.trainingdairy.domain.model.DiaryEntryModel
import ru.ikon.trainingdairy.domain.model.NoteModel
import ru.ikon.trainingdairy.ui.day.DayFragment
import ru.ikon.trainingdairy.ui.month.MonthContract
import java.text.SimpleDateFormat
import java.util.*

class NoteDialogFragment(var id: Long? = null, var currentDate: Date? = null) : DialogFragment(), NoteContract.View{

    private var _binding: DialogFragmentNoteBinding? = null
    private val binding: DialogFragmentNoteBinding get() { return _binding!! }

    private lateinit var presenter: NoteContract.Presenter

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

        var dateTemp = Date()

        if (currentDate != null) {
            binding.editTextDate.setText(currentDate?.let {
                SimpleDateFormat(
                    "dd MMMM yyyy",
                    Locale.ENGLISH
                ).format(it)
            })
        }

        id?.let {
            binding.editTextDate.setText(SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(presenter.getNote(it).date))
            binding.editTextBody.setText(presenter.getNote(it).text)
        }

        with(binding) {
            editTextDate.setOnClickListener {
                Calendar.getInstance().get(Calendar.YEAR)
                DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, day ->
                    editTextDate.setText("" + day + "/" + month + "/" + year)
                    dateTemp = GregorianCalendar(year, month, day).time
                }, Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            okButton.setOnClickListener {
                if (editTextDate.text.toString() != "" && editTextBody.text.toString() != "") {
                    presenter.saveNote(dateTemp, editTextBody.text.toString())
                    dismiss()
                }
            }
        }

    }

    companion object {
        const val TAG = "NoteDialogFragment"
    }
}