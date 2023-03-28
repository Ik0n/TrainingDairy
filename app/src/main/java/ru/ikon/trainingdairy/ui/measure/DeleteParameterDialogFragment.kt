package ru.ikon.trainingdairy.ui.measure

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ru.ikon.trainingdairy.R
import java.lang.IllegalStateException

class DeleteParameterDialogFragment : DialogFragment()  {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            AlertDialog.Builder(it)
                .setTitle(getString(R.string.measure_delete_dialog_title))
                .setMessage(getString(R.string.measure_delete_dialog_message))
                .setPositiveButton(getString(R.string.delete_dialog_positive_button)) { dialog, id, ->
                    dialog.cancel()
                }
                .setNegativeButton(getString(R.string.delete_dialog_negative_button)) { dialog, id ->
                    dialog.cancel()
                }
                .create()
        } ?: throw IllegalStateException(getString(R.string.measure_delete_dialog_error_message))
    }

}