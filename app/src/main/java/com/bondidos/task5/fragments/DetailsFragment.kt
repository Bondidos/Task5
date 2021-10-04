package com.bondidos.task5.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bondidos.task5.MainActivity
import com.bondidos.task5.R
import com.bondidos.task5.api.Cat
import com.bondidos.task5.databinding.FragmentDetailsBinding
import com.bondidos.task5.model.CatListService
import com.bondidos.task5.utils.downloadAndSave
import com.bumptech.glide.Glide

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val catListService: CatListService by activityViewModels()
    private var navigation: FragmentNavigation? = null
    private val cat: Cat? get() = catListService.getCat()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setImageAndText()
        initButton()
    }

    private fun setImageAndText() {

        with(binding) {
            // load image
            Glide.with(catView)
                .load(cat?.url)
                .placeholder(R.drawable.ic_baseline_360_24)
                .error(R.drawable.ic_baseline_error_24)
                .into(catView)

            // load Description
            description.text = cat?.breeds?.let {
                if (!it.isEmpty()) {
                    it[0].description
                } else "No Description"
            }.toString()
        }
    }

    private fun initButton() {
        binding.btnSave.setOnClickListener {
            downloadAndSave(requireNotNull(context), requireNotNull(cat))
        }
        // todo share image
        binding.btnShare.setOnClickListener {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, cat?.url)
                type = "image/*"
            } // resources.getText(R.string.send_to)
            startActivity(Intent.createChooser(shareIntent, "Share Cat"))
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
