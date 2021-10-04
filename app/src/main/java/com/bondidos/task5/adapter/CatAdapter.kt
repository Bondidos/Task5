package com.bondidos.task5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bondidos.task5.api.Cat
import com.bondidos.task5.databinding.CatItemBinding

class CatAdapter : RecyclerView.Adapter<CatViewHolder>() {

    var cats = emptyList<Cat>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    val catForDetails: MutableLiveData<Cat> = MutableLiveData()

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
        // onClick open details
        holder.itemView.setOnClickListener {
            catForDetails.value = cats[position]
        }
    }
}
