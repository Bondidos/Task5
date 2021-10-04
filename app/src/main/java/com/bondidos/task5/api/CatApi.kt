package com.bondidos.task5.api

import retrofit2.http.GET
import retrofit2.http.Query

const val QUERY = "/v1/images/search?"
const val ORDER = "order=DESC"

interface CatApi {

    @GET(QUERY + ORDER)
    suspend fun getListCats(
        @Query("limit") n: Int,
        @Query("page") p: Int
    ): List<Cat>
}
