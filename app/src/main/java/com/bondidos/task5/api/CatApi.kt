package com.bondidos.task5.api

import retrofit2.http.GET
import retrofit2.http.Query

private const val QUERY = "/v1/images/search?"
private const val ORDER = "order=DESC"
private const val PAGE = "page"
private const val LIMIT = "limit"

interface CatApi {

    @GET(QUERY + ORDER)
    suspend fun getListCats(
        @Query(LIMIT) n: Int,
        @Query(PAGE) p: Int
    ): List<Cat>
}
