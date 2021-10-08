package com.bondidos.task5.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bondidos.task5.model.CatListService
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var observer: Observer<List<Cat>>
    private lateinit var catListService: CatListService
    @ExperimentalCoroutinesApi
    private val coroutineDispatcher = TestCoroutineDispatcher()
    @ExperimentalCoroutinesApi
    private val testCoroutineScope = TestCoroutineScope(coroutineDispatcher)
    private lateinit var repository: Repository


    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(coroutineDispatcher)
        repository = Repository()
        catListService = CatListService(repository)
        observer = mockk(relaxed = true)
        catListService.cats.observeForever(observer)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun get_five_pages_from_api_into_observer() {
        testCoroutineScope.launch {
            (1..5).forEach {
                catListService.getNextPage()
                delay(100)                              // wait list from repo
                verify {
                    observer.onChanged(match { list ->
                        list.size == it * 10
                    })
                }
            }
        }
    }
}
