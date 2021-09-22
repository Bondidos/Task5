package com.bondidos.task5.api

import retrofit2.http.GET

interface CatApi {
    @GET("/v1/images/search?format=json")//?limit=20&page=0
    suspend fun getListCats(): ApiData
}