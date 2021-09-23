package com.bondidos.task5.api

import android.app.Application
import com.bondidos.task5.adapter.cat_holder.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class App: Application() {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.thecatapi.com")
        .build()

    private val catService = retrofit.create(CatApi::class.java)

    suspend fun getListCats(limit: Int,page: Int): List<Cat> {
          return withContext(Dispatchers.IO) {
             catService.getListCats(limit, page).body() ?: emptyList()
        }
    }
}