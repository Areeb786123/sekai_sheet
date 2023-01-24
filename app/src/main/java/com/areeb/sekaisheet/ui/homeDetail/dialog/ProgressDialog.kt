package com.areeb.sekaisheet.ui.homeDetail.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.sekaisheet.databinding.AlterDialogueLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProgressDialog @Inject constructor() : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.setCanceledOnTouchOutside(false)// change it to false
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent);
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val binding = AlterDialogueLayoutBinding.inflate(layoutInflater)
        builder.setView(binding.root)
        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            delay(6000)
            dismiss()
        }
    }
}