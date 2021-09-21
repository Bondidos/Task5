package com.bondidos.task5.adapter.cat_holder

import androidx.recyclerview.widget.RecyclerView
import com.bondidos.task5.databinding.CatItemBinding

class CatViewHolder(binding: CatItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val imageView = binding.catView
    val details = binding.catDetails

    fun onBind(item: Cat){
        //todo load image using Glide in to imageView
        //todo load details from json
    }
}