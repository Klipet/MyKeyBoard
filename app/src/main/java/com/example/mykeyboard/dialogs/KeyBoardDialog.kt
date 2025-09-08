package com.example.mykeyboard.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.mykeyboard.databinding.KeyBoardDialogBinding


class KeyBoardDialog: DialogFragment()  {
    private var _binding: KeyBoardDialogBinding? = null
    private val binding get() = _binding!!


    companion object {
        private const val ARG_IMAGE_RES = "image_res"
        fun newInstance(imageRes: Int): KeyBoardDialog {
            val fragment = KeyBoardDialog()
            val args = Bundle()
            args.putInt(ARG_IMAGE_RES, imageRes)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = KeyBoardDialogBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageRes = arguments?.getInt(ARG_IMAGE_RES) ?: 0
        if (imageRes != 0) {
           binding.imDialogPhoto.setImageResource(imageRes)
        }

        setupClickListeners()
    }
    private fun setupClickListeners() {
        binding.btClosed.setOnClickListener {
            dismiss()
        }
        binding.imDialogBt.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}