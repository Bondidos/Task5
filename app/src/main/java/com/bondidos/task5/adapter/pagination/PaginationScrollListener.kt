package com.bondidos.task5.adapter.pagination

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(private val layoutManager: GridLayoutManager) :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        // Return the current number of child views attached to the parent RecyclerView.
        // This does not include child views that were temporarily detached and/or scrapped.
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        saveFirstVisibleItemPosition(firstVisibleItemPosition)

        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount &&
            firstVisibleItemPosition >= 0
        ) {
            loadNextPage()
        }
    }

    protected abstract fun loadNextPage()
    protected abstract fun saveFirstVisibleItemPosition(position: Int)
}
