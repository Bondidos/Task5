package com.bondidos.task5.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondidos.task5.api.App
import kotlinx.coroutines.launch
const val TAG ="ViewModel"
class CatViewModel: ViewModel() {



    /*private val _items = MutableLiveData<List<Cat>>()
    val cats: LiveData<List<Cat>>
    get() = _items

    init{
        viewModelScope.launch {
            _items.value = App().getListCats(limit,page)
            Log.d(TAG,"init")
        }

    }

    private val catToDetailsFragment: MutableLiveData<Cat> = MutableLiveData()
    fun setCat(cat: Cat){
        catToDetailsFragment.value = cat
    }
    fun getCat() = catToDetailsFragment.value



    fun loadNextPage(){
        viewModelScope.launch {
            page++
            _items.value = App().getListCats(limit,page)
            Log.d(TAG,"NextPage")
        }
    }*/
}