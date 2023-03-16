package ru.ikon.trainingdairy.ui.measure

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class DeleteParameterDialogFragment : DialogFragment()  {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            AlertDialog.Builder(it)
                .setTitle("Подтверждение")
                .setMessage("Вы уверены что хотите удалить?")
                .setPositiveButton("Удалить") { dialog, id, ->
                    dialog.cancel()
                }
                .setNegativeButton("Отмена") { dialog, id ->
                    dialog.cancel()
                }
                .create()
        } ?: throw IllegalStateException("Activity can't be null")
    }

}