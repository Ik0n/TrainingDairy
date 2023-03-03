package ru.ikon.trainingdairy.ui.note

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import ru.ikon.trainingdairy.R
import ru.ikon.trainingdairy.databinding.DialogFragmentNoteBinding
import ru.ikon.trainingdairy.ui.month.MonthContract
import java.util.*

class NoteDialogFragment(var date: Date = Date()) : DialogFragment(), NoteContract.View {

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

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
                .setMessage("Заметка")
                .setPositiveButton("Сохранить") {_,_ ->
                    presenter.saveNote(Date(), binding.editTextBody.text.toString())
                }
                .setView(R.layout.dialog_fragment_note)
                .create()
    }


    companion object {
        const val TAG = "NoteDialogFragment"
    }
}