package com.bondidos.task5.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.bondidos.task5.api.Cat
import com.bondidos.task5.fragments.CatListFragment
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.stub.StubRepository
import io.mockk.isMockKMock
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CatListServiceTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var observer: Observer<List<Cat>>
    private lateinit var catListService: CatListService
    @ExperimentalCoroutinesApi
    private val coroutineDispatcher = TestCoroutineDispatcher()
    private var recievedList = listOf<Cat>(mockk())

    @ExperimentalCoroutinesApi
    @Before
    fun setup(){
        Dispatchers.setMain(coroutineDispatcher)
        catListService = CatListService()
        observer = mockk(relaxed = true)
        catListService.cats.observeForever(observer)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun test_init_block(){
        catListService.getNextPage()
        //every { observer.onChanged (Result.success(List<Cat>)) }
    }
    @Test
    fun get_next_page(){

    }
}


