package com.bondidos.task5.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.bondidos.task5.MainActivity
import com.bondidos.task5.adapter.CatAdapter
import com.bondidos.task5.adapter.PaginationScrollListener
import com.bondidos.task5.databinding.FragmentCatsListBinding
import com.bondidos.task5.model.CatViewModel

const val TAG ="CatListFragment"

class CatListFragment : Fragment() {

    private var _binding: FragmentCatsListBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val catViewModel: CatViewModel by activityViewModels()
    private var navigation: FragmentNavigation? = null
    private val catAdapter = CatAdapter()

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
                //pagination
                addOnScrollListener(object: PaginationScrollListener(layoutManager as LinearLayoutManager){
                    override fun loadNextPage() {
                        catViewModel.loadNextPage()
                    }
                })
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
        //Old pagination (Kostil')
        /*catAdapter.page.observe(viewLifecycleOwner){page ->
            catViewModel.loadNextPage()
        }*/
        catAdapter.catForDetails.observe(viewLifecycleOwner){cat ->
            catViewModel.setCat(cat)
            navigation?.navigateDetailsFragment()
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