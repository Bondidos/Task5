package com.bondidos.task5.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bondidos.task5.MainActivity
import com.bondidos.task5.adapter.CatAdapter
import com.bondidos.task5.adapter.pagination.PaginationScrollListener
import com.bondidos.task5.databinding.FragmentCatsListBinding
import com.bondidos.task5.model.CatListService

class CatListFragment : Fragment() {

    private var _binding: FragmentCatsListBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var navigation: FragmentNavigation? = null
    private lateinit var catAdapter: CatAdapter
    private val catListService: CatListService by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation = context as MainActivity
        catAdapter = CatAdapter(navigation)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initObserver()
    }

    private fun initRecyclerView() {

        with(binding) {
            recycler.apply {
                layoutManager = GridLayoutManager(root.context, 2)
                adapter = catAdapter
                addOnScrollListener(object :
                    PaginationScrollListener(layoutManager as GridLayoutManager) {

                    override fun loadNextPage() {
                        catListService.getNextPage()
                    }

                    override fun saveFirstVisibleItemPosition(position: Int) {
                        catListService.firstVisibleItem = position
                    }
                })
                recycler.scrollToPosition(catListService.firstVisibleItem)
            }
        }
    }

    private fun initObserver() {
        catListService.cats.observe(viewLifecycleOwner) { list ->
            list.let {
                catAdapter.cats = it
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
