package com.bondidos.task5.model

import com.bondidos.task5.api.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

typealias CatListener = (cats: List<Cat>) -> Unit

class CatListService {
    private var page = -1
    private val limit = 10

    private var cats = mutableListOf<Cat>()

    //operation of modification
    fun getNextPage() {
        CoroutineScope(Dispatchers.IO).launch {
            page++
            cats.addAll(App().getListCats(limit,page))
        }
        notifyCatListeners()
    }
    //get first page on app running
    init {
        getNextPage()
    }

    //list of listeners, which track to all changes in this class
    private val listeners = mutableSetOf<CatListener>()

    fun getCats(): List<Cat> = cats

    fun addListener (listener: CatListener) {
        listeners.add(listener)
        listener.invoke(cats)
    }
    fun removeListener(listener: CatListener) = listeners.remove (listener)
    private fun notifyCatListeners() = listeners.forEach { it.invoke(cats) }            //hand over a new list to the listeners
}