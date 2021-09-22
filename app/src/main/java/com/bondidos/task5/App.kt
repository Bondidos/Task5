package com.bondidos.task5

import android.app.Application
import com.bondidos.task5.adapter.cat_holder.Cat
import com.bondidos.task5.api.CatApi
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
    private val list = mutableListOf<Cat>()

    suspend fun getListCats(): List<Cat>{
        /*return withContext(Dispatchers.IO){
            catService.getListCats()
                .list
                .map { catData ->
                    Cat(
                        catData.id,
                        catData.picture
                    )
                }
        }*/
        return withContext(Dispatchers.IO){
            val item = catService.getListCats()
            list.add(
                Cat(
                    item.id,
                    item.url
                )
            )
            list
        }
    }
}