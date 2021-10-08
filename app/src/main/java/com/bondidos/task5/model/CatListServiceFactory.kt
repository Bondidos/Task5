package com.bondidos.task5.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bondidos.task5.api.Repository
import java.lang.IllegalArgumentException

class CatListServiceFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatListService::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CatListService(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
