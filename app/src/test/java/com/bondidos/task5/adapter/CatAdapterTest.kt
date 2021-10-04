package com.bondidos.task5.adapter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bondidos.task5.api.Cat
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class CatAdapterTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var catForDetails: MutableLiveData<Cat>
    private lateinit var catAdapter: CatAdapter
    private lateinit var observer: Observer<Cat>
    private lateinit var cat: Cat

    @Before
    fun setup(){
        catForDetails = MutableLiveData()
        catAdapter = CatAdapter()
        observer = mockk()
        cat = Cat("1","some url", emptyList())
        catAdapter.catForDetails.observeForever(observer)
    }

    @Test
    fun getCats() {

    }

    @Test
    fun getCatForDetails() {
        catAdapter.catForDetails.value = cat
        verify {  }
    }
}
