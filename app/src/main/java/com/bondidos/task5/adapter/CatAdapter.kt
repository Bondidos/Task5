package com.bondidos.task5.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData

import com.bondidos.task5.adapter.cat_holder.Cat
import com.bondidos.task5.adapter.cat_holder.CatViewHolder
import com.bondidos.task5.databinding.CatItemBinding
import com.bondidos.task5.fragments.FragmentNavigation
import com.bondidos.task5.model.CatViewModel
import kotlinx.coroutines.withContext

const val TAG="CatAdapter"
class CatAdapter: RecyclerView.Adapter<CatViewHolder>() {

    private val cats = mutableListOf<Cat>()
    val page: MutableLiveData<Int> = MutableLiveData(0)
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
        val pictureUrl = cats[position].picture
        holder.onBind(pictureUrl)
        holder.itemView.setOnClickListener {
            catForDetails.value = cats[position]
        }
    }

    fun addItems(newItems: List<Cat>) {
        cats.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: CatViewHolder) {
        super.onViewAttachedToWindow(holder)
        if(holder.absoluteAdapterPosition == cats.size-3) {
            Log.d(TAG,"cat.size = ${cats.size}, attached to window: ${holder.absoluteAdapterPosition}")
            page.value = +1
        }
    }
}