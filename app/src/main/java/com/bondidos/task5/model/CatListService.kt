package com.bondidos.task5.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.task5.api.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//typealias CatListener = (cats: List<Cat>) -> Unit

class CatListService: ViewModel() {
    private var page = 0
    private val limit = 10
    var firstVisibleItem = 0
    private val catToDetailsFragment: MutableLiveData<Cat> = MutableLiveData()

    private val _cats = MutableLiveData<List<Cat>>()
    val cats: LiveData<List<Cat>>
    get() = _cats

   init{
       viewModelScope.launch {
           _cats.value = App().getListCats(limit,page)
       }
   }
    //operation of modification
    fun getNextPage() {
        viewModelScope.launch {
            page++
            val list = mutableListOf<Cat>()
            list.apply {
                addAll(_cats.value?: emptyList())
                addAll(App().getListCats(limit,page))
            }
            _cats.value = list
        }
    }

    fun setCat(cat: Cat){
        catToDetailsFragment.value = cat
    }
    fun getCat() = catToDetailsFragment.value
}