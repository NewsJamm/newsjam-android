package com.example.newsjam_android.ui.view.dialog

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.newsjam_android.R
import com.example.newsjam_android.databinding.DialogWithdrawBinding
import com.example.newsjam_android.ui.view.listener.DialogConfirmListener

class WithDrawDialog(
    dialogConfirmListener: DialogConfirmListener
) : DialogFragment() {
    private var _binding: DialogWithdrawBinding? = null
    private val binding get() = _binding!!

    private var dialogConfirmListener: DialogConfirmListener? = null

    init {
        this.dialogConfirmListener = dialogConfirmListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogWithdrawBinding.inflate(inflater, container, false)
        val view = binding.root
        dialog?.window?.setBackgroundDrawableResource(R.drawable.shape_fill_white_square_rounded_8dp)

        binding.dialogWithdrawConfirmBt.setOnClickListener {
            this.dialogConfirmListener?.onClick()
            dismiss()
        }
        binding.dialogWithdrawCancelBt.setOnClickListener {
            dismiss()
        }
        return view
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}