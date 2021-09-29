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

    private val catToDetailsFragment: MutableLiveData<Cat> = MutableLiveData()
    fun setCat(cat: Cat){
        catToDetailsFragment.value = cat
    }
    fun getCat() = catToDetailsFragment.value
}