package com.bondidos.task5.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bondidos.task5.adapter.CatAdapter
import com.bondidos.task5.databinding.FragmentCatsListBinding
import com.bondidos.task5.model.CatViewModel


class CatListFragment : Fragment() {

    private var _binding: FragmentCatsListBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val catAdapter = CatAdapter()
    private val catViewModel: CatViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //receive list for recycler from viewModel
        initRecyclerView()
    }

    private fun initRecyclerView(){
        with(binding){
            recycler.apply {
                layoutManager = LinearLayoutManager(root.context)
                adapter = catAdapter
            }
        }
    }
    private fun initObserver(){
        catViewModel.cats.observe(viewLifecycleOwner){
            list ->
            list.let{
                catAdapter.addItems(it)
            }
        }
    }
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {

        @JvmStatic
        fun newInstance() = CatListFragment()
    }
}