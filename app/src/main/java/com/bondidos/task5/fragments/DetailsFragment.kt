package com.bondidos.task5.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.Fragment
import com.bondidos.task5.api.Cat
import com.bondidos.task5.api.Repository
import com.bondidos.task5.databinding.FragmentDetailsBinding
import com.bondidos.task5.MainActivity
import com.bondidos.task5.model.CatListService
import com.bondidos.task5.model.CatListServiceFactory
import com.bondidos.task5.R
import com.bondidos.task5.utils.DownloadAndSaveImage
import com.bumptech.glide.Glide

private const val CAT_ID = "catId"
private const val DEFAULT_DESCRIPTION = "No description"

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val catListService: CatListService by activityViewModels {
        CatListServiceFactory(Repository())
    }
    private var navigation: FragmentNavigation? = null
    private var catId: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // get cat id
        arguments?.let { catId = it.getString(CAT_ID) }

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cat = findCatById()
        cat?.let {
            setImageAndText(cat)
            initButton(cat)
        }
    }

    private fun setImageAndText(cat: Cat) {

        with(binding) {
            // load image
            Glide.with(catView)
                .load(cat.url)
                .placeholder(R.drawable.ic_baseline_360_24)
                .error(R.drawable.ic_baseline_error_24)
                .into(catView)

            // load Description
            description.text = cat.breeds.let {
                if (it.isNotEmpty()) {
                    it.first().description
                } else DEFAULT_DESCRIPTION
            }.toString()
        }
    }

    private fun initButton(cat: Cat) {

        binding.btnSave.setOnClickListener {
            try {
                DownloadAndSaveImage(requireContext(),cat).downloadAndSave()
            } catch (e: IllegalStateException) {
                e.stackTrace
            }
        }
    }

    private fun findCatById(): Cat? {
        val catList = catListService.cats.value ?: emptyList()
        return catList.find { it.id == catId }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String) = DetailsFragment().apply {
            arguments = bundleOf(Pair(CAT_ID, id))
        }
    }
}
