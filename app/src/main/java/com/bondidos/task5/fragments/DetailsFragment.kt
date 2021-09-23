package com.bondidos.task5.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bondidos.task5.MainActivity
import com.bondidos.task5.R
import com.bondidos.task5.databinding.FragmentDetailsBinding
import com.bondidos.task5.model.CatViewModel
import com.bumptech.glide.Glide

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val catViewModel: CatViewModel by activityViewModels()
    private var navigation: FragmentNavigation? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation = context as MainActivity
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setImage()
    }

    private fun setImage(){
        val cat = catViewModel.getCat()
        with(binding){

        Glide.with(catView)
            .load(cat?.picture)
            .placeholder(R.drawable.ic_baseline_360_24)
            .error(R.drawable.ic_baseline_error_24)
            .into(catView)
        }
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