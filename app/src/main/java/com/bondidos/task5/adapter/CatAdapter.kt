package com.bondidos.task5.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.bondidos.task5.adapter.cat_holder.Cat
import com.bondidos.task5.adapter.cat_holder.CatViewHolder
import com.bondidos.task5.databinding.CatItemBinding

class CatAdapter() : RecyclerView.Adapter<CatViewHolder>() {

    private val cats = mutableListOf<Cat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {

        return CatViewHolder(
            CatItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int {
        return cats.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val pictureUrl = cats[position].picture
        holder.onBind(pictureUrl)
    }

    fun addItems(newItems: List<Cat>) {
        cats.addAll(newItems)
        notifyDataSetChanged()
    }



}