package com.bondidos.task5.api

import retrofit2.http.GET
import retrofit2.http.Query

const val KEY_HEADER = "x-api-key: 5c353ca9-8022-4ed1-880a-ae133a691483"
const val QUERY = "/v1/images/search?"
const val LIMIT = "limit=10"
const val PAGE = "page=0"
const val ORDER = "order=DESC"

interface CatApi {
    // "$QUERY?$LIMIT&${PAGE}&$ORDER"
    // @Headers(KEY_HEADER)
    @GET(QUERY + ORDER)
    suspend fun getListCats(
        @Query("limit") n: Int,
        @Query("page") p: Int
    ): List<Cat>
}
