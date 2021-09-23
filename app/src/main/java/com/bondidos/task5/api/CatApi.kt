package com.bondidos.task5.api

import com.bondidos.task5.adapter.cat_holder.Cat
import retrofit2.Response
import retrofit2.http.GET

interface CatApi {
    @GET("/v1/images/search?limit=10&page=10&order=DESC")//?limit=20&page=0
    suspend fun getListCats(): Response<List<Cat>>
}