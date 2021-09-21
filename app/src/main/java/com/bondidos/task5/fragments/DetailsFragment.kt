package com.bondidos.task5.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bondidos.task5.R
import com.bondidos.task5.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
    companion object {

        @JvmStatic
        fun newInstance() = DetailsFragment()
    }
}