package com.bondidos.task5.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bondidos.task5.R
import com.bondidos.task5.databinding.CatItemBinding
import com.bumptech.glide.Glide

class CatViewHolder(binding: CatItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val imageView = binding.catView

    fun onBind(pictureUrl: String) {

        Glide.with(imageView)
            .load(pictureUrl)
            .placeholder(R.drawable.ic_baseline_360_24)
            .error(R.drawable.ic_baseline_error_24)
            .into(imageView)
    }
}
