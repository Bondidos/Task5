package com.bondidos.task5.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.task5.adapter.cat_holder.Cat
import kotlinx.coroutines.launch

class CatViewModel: ViewModel() {

    private val _items = MutableLiveData<List<Cat>>()
    val cats: LiveData<List<Cat>>
    get() = _items

    init{
        viewModelScope.launch {
            //_items.value = //todo get from API
        }
    }
}