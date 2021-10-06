package com.bondidos.task5.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.task5.api.App
import com.bondidos.task5.api.Cat
import kotlinx.coroutines.launch

private const val LIMIT = 10

class CatListService : ViewModel() {

    private val repository get() = App()                    // call ethernet ;)
    private var page = 0                                    // start page
    var firstVisibleItem = 0                                // first visible item of RV. Using to remember scroll position

    private val _cats = MutableLiveData<List<Cat>>()
    val cats: LiveData<List<Cat>>
        get() = _cats

    init {
        viewModelScope.launch {
            _cats.value = repository.getListCats(LIMIT, page)
        }
    }

    fun getNextPage() {
        viewModelScope.launch {
            page++
            val list = mutableListOf<Cat>()
            list.apply {
                addAll(_cats.value ?: emptyList())
                addAll(repository.getListCats(LIMIT, page))
            }
            _cats.value = list
        }
    }
}
