package com.bondidos.task5.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.task5.api.Repository
import com.bondidos.task5.api.Cat
import kotlinx.coroutines.launch

private const val LIMIT = 10

class CatListService(private val repository: Repository) : ViewModel() {

    // start page
    private var page = 0
    // first visible item of RV. Using to remember scroll position
    var firstVisibleItem = 0

    private val _cats = MutableLiveData<List<Cat>>()
    val cats: LiveData<List<Cat>>
        get() = _cats

    init {
        viewModelScope.launch {
            _cats.value = getPage()
        }
    }

    fun getNextPage() {
        viewModelScope.launch {
            page++
            val list = mutableListOf<Cat>()
            list.apply {
                addAll(_cats.value ?: emptyList())
                addAll(getPage())
            }
            _cats.value = list
        }
    }

    private suspend fun getPage(): List<Cat> {
        try {
            return repository.getListCats(LIMIT, page)
        } catch (e: Throwable) {
            e.stackTrace
        }
        return emptyList()
    }
}
