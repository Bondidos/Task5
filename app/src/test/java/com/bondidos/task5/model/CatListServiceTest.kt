package com.bondidos.task5.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bondidos.task5.api.Cat
import com.bondidos.task5.api.Repository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CatListServiceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var observer: Observer<List<Cat>>
    private lateinit var catListService: CatListService

    @ExperimentalCoroutinesApi
    private val coroutineDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    private val testCoroutineScope = TestCoroutineScope(coroutineDispatcher)
    private lateinit var repository: Repository
    private var result = listOf<Cat>(mockk(), mockk())

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(coroutineDispatcher)
        repository = mockk()
        catListService = CatListService(repository)
        observer = mockk(relaxed = true)
        catListService.cats.observeForever(observer)
    }

    @Test
    fun get_next_page_calls_observer_onChange() {
        catListService.getNextPage()
        verify { observer.onChanged(any()) }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun repository_pass_data_to_the_Observer() {
        testCoroutineScope.launch {
            coEvery { repository.getListCats(any(), any()) } returns result
        }
        catListService.getNextPage()
        verify {
            observer.onChanged(match { list ->
                list == result
            })
        }
    }
}
