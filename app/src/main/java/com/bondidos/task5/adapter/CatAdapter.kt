package com.bondidos.task5.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bondidos.task5.api.Cat
import com.bondidos.task5.databinding.CatItemBinding
import com.bondidos.task5.fragments.FragmentNavigation

class CatAdapter(private val navigation: FragmentNavigation?) : RecyclerView.Adapter<CatViewHolder>() {

    var cats = emptyList<Cat>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

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
        val pictureUrl = cats[position].url
        holder.onBind(pictureUrl)

        holder.itemView.setOnClickListener {
            navigation?.navigateDetailsFragment(id = cats[position].id)
        }
    }
}
