package com.bondidos.task5.fragments

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bondidos.task5.MainActivity
import com.bondidos.task5.R
import com.bondidos.task5.adapter.CatAdapter
import com.bondidos.task5.adapter.PaginationScrollListener
import com.bondidos.task5.api.App
import com.bondidos.task5.databinding.FragmentCatsListBinding
import com.bondidos.task5.model.CatListService

import com.bondidos.task5.model.CatViewModel

const val TAG ="CatListFragment"

class CatListFragment : Fragment() {

    private var _binding: FragmentCatsListBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val catViewModel: CatViewModel by activityViewModels()
    private var navigation: FragmentNavigation? = null
    private var catAdapter = CatAdapter()
    private val catListService: CatListService by activityViewModels()
    //get() = (context?.applicationContext as App).catListService

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
   /* private val catListener: CatListener = {
        catAdapter.cats = it
    }*/

    private fun initRecyclerView(){

        with(binding){
            recycler.apply {
                layoutManager = GridLayoutManager(root.context,2)
                    //LinearLayoutManager(root.context)
                adapter = catAdapter
                //pagination
                addOnScrollListener(object: PaginationScrollListener(layoutManager as GridLayoutManager){
                    override fun loadNextPage() {
                        catListService.getNextPage()
                    }

                    override fun saveFirstVisibleItemPosition(position: Int) {
                        catListService.firstVisibleItem = position
                    }
                })
                scrollToPosition(catListService.firstVisibleItem)
            }
        }
    }


    private fun initObserver(){
        catListService.cats.observe(viewLifecycleOwner){
            list ->
            list.let{
                catAdapter.cats = it
            }
        }

        catAdapter.catForDetails.observe(viewLifecycleOwner){cat ->
            catViewModel.setCat(cat)
            navigation?.navigateDetailsFragment()
        }
        //catListService.addListener(catListener)

    }
    override fun onDestroy() {
       // catListService.removeListener(catListener)
        _binding = null
        super.onDestroy()
    }

    companion object {

        @JvmStatic
        fun newInstance() = CatListFragment()
    }
}