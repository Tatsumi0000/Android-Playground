package com.example.communicating_with_fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.example.communicating_with_fragments.databinding.Fragment1Binding
import com.example.communicating_with_fragments.databinding.FragmentCustomDialogBinding


class CustomFragmentDialog : DialogFragment() {

    private var _binding: FragmentCustomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        _binding = FragmentCustomDialogBinding.inflate(requireActivity().layoutInflater)
        val dialog = Dialog(requireActivity())
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(binding.root)
        dialog.setCanceledOnTouchOutside(false)
        isCancelable = false
        binding.doneButton.setOnClickListener {
            val randomInt = (0..100).random()
            val bundle = bundleOf(bundleKey to randomInt)
            setFragmentResult(requestKey, bundle)
            dismiss()
        }
        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        const val bundleKey = "customFragmentDialogBundleKey"
        const val requestKey = "customFragmentDialogRequestKey"
    }

}