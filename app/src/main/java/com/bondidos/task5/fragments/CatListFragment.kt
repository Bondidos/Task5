package com.bondidos.task5.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.bondidos.task5.adapter.CatAdapter
import com.bondidos.task5.databinding.FragmentCatsListBinding
import com.bondidos.task5.model.CatViewModel

const val TAG ="CatListFragment"

class CatListFragment : Fragment() {

    private var _binding: FragmentCatsListBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val catViewModel: CatViewModel by activityViewModels()
    private val catAdapter = CatAdapter()


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
        initObserver()
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
        catAdapter.page.observe(viewLifecycleOwner){page ->
            catViewModel.loadNextPage()
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