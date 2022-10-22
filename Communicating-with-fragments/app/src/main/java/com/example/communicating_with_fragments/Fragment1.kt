package com.example.communicating_with_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.communicating_with_fragments.databinding.Fragment1Binding
import com.google.android.material.snackbar.Snackbar

class Fragment1 : Fragment() {

    private var _binding: Fragment1Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment1Binding.inflate(inflater, container, false)
        val view = binding.root

        binding.fragment1Button.setOnClickListener {
            val action = Fragment1Directions.actionFragment1ToCustomFragmentDialog()
            view.findNavController().navigate(action)
        }

        setFragmentResultListener(CustomFragmentDialog.requestKey) { _, bundle ->
            val receiveInt = bundle.getInt(CustomFragmentDialog.bundleKey, -1)
            Snackbar.make(
                requireActivity().findViewById(R.id.activity_main),
                receiveInt.toString(),
                Snackbar.LENGTH_LONG
            ).show()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}