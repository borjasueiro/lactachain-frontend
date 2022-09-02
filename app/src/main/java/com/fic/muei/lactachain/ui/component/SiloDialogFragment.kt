package com.fic.muei.lactachain.ui.component

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.fic.muei.lactachain.R
import com.fic.muei.lactachain.ui.LactachainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SiloDialogFragment: DialogFragment(){
    private val viewModel: LactachainViewModel by activityViewModels()
    companion object {
        const val TAG = "SiloDialogFragment"
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val array: Array<String> = resources.getStringArray(R.array.silo_type)
            var selection:String = ""
            builder.setTitle(R.string.select_silo)
                .setSingleChoiceItems(array, -1,
                    DialogInterface.OnClickListener{ _, which ->
                    selection = array[which]

                    })
                // Set the action buttons
                .setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { dialog, _ ->
                        viewModel.addFinalSilo(selection)
                        dialog.dismiss()
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, _ ->dialog.dismiss()
                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}