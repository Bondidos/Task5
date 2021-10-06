package com.bondidos.task5.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.bondidos.task5.api.Cat
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CatListServiceTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var observer: Observer<Cat>
    private lateinit var catListService: CatListService

    @Before
    fun setup(){
        catListService = mockk()
        observer = mockk()
    }
}


