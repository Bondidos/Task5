package com.bondidos.task5.api

import android.app.Application
import com.bondidos.task5.model.Cat
import com.bondidos.task5.model.CatListService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class App: Application() {

    //catService
    val catListService = CatListService()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.thecatapi.com")
        .build()

    private val catNetworkService = retrofit.create(CatApi::class.java)

    //todo add check for response code
    suspend fun getListCats(limit: Int,page: Int): List<Cat> {
          return withContext(Dispatchers.IO) {
             catNetworkService.getListCats(limit, page).body() ?: emptyList()
        }
    }
}