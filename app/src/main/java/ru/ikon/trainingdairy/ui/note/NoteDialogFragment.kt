package ru.ikon.trainingdairy.ui.note

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.app
import ru.ikon.trainingdairy.databinding.DialogFragmentNoteBinding
import ru.ikon.trainingdairy.domain.model.NoteModel
import ru.ikon.trainingdairy.utils.DATE
import ru.ikon.trainingdairy.utils.NOTE_ID
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NoteDialogFragment() :
    DialogFragment(), NoteContract.View {

    private var _binding: DialogFragmentNoteBinding? = null
    private val binding: DialogFragmentNoteBinding
        get() {
            return _binding!!
        }

    @Inject
    lateinit var presenter: NoteContract.Presenter

    private lateinit var currentDate : Date
    private var id : Long = 0

    /** Календарь, который будет использован для выбора даты  */
    private var calendar = Calendar.getInstance()

    override fun showData(note: NoteModel) {
        binding.editTextDate.setText(
            SimpleDateFormat(
                getString(R.string.date_format),
                Locale.ENGLISH
            ).format(note.date)
        )
        binding.editTextBody.setText(note.text)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireContext().app.di.inject(this)
        presenter.attach(this)

        arguments?.let {
            currentDate = Date(it.getLong(DATE))
            it.getLong(NOTE_ID).let {
                id = it
            }
        }

        _binding = DialogFragmentNoteBinding.inflate(LayoutInflater.from(context))

        presenter.onCreate(id, currentDate)
    }

    /**
     * Обработчик нажатия на поле выбора даты
     */
    private fun onDateEditTextClicked() {
        with(binding) {
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    calendar[Calendar.YEAR] = year
                    calendar[Calendar.MONTH] = monthOfYear
                    calendar[Calendar.DAY_OF_MONTH] = dayOfMonth

                    val sdf = SimpleDateFormat(getString(R.string.date_format))
                    editTextDate.setText(sdf.format(calendar.time))

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

        private lateinit var listener: OnOkButtonClickListener

        fun newInstance(currentDate: Long, id: Long? = null) : DialogFragment {
            return NoteDialogFragment().apply {
                arguments = Bundle().apply {
                    putLong(DATE, currentDate)
                    id?.let { putLong(NOTE_ID, it) }
                }
            }
        }

        fun setOnOkButtonClickListener(listener: OnOkButtonClickListener) {
            this.listener = listener
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val editTextBody = binding.editTextBody
        val editTextDate = binding.editTextDate

        /* editTextDate.setText(
            SimpleDateFormat(
                getString(R.string.date_format),
                Locale.ENGLISH
            ).format(currentDate)
        )

        id.let {
            if (id != 0.toLong()) {
                binding.editTextDate.setText(
                    SimpleDateFormat(
                        getString(R.string.date_format),
                        Locale.ENGLISH
                    ).format(presenter.getNote(it).date)
                )
                binding.editTextBody.setText(presenter.getNote(it).text)
            }
        } */

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

        return AlertDialog.Builder(requireActivity())
            .setTitle(getString(R.string.note_dialog_fragment_title))
            .setView(binding.root)
            .setPositiveButton(getString(R.string.note_dialog_fragment_positive_button), object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
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

            })
            .setNegativeButton(getString(R.string.note_dialog_fragment_negative_button), null)
            .create()
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