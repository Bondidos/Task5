package com.bondidos.task5.api

import android.app.Application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.thecatapi.com"

class App : Application() {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val catNetworkService = retrofit.create(CatApi::class.java)

    // todo add check for response code
    //TODO : TRY CATCH FINALLY??? LOOK ASSASINUS EXAMPLE
    suspend fun getListCats(limit: Int, page: Int): List<Cat> {
        return withContext(Dispatchers.IO) {
            catNetworkService.getListCats(limit, page)
        }
    }
}
