package com.bondidos.task5.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bondidos.task5.MainActivity
import com.bondidos.task5.databinding.FragmentCustomDialogBinding

class ExitDialog : DialogFragment() {

    private var _binding: FragmentCustomDialogBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var navigation: FragmentNavigation? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnYes.setOnClickListener { navigation?.finish() }
            btnNo.setOnClickListener { dismiss() }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
