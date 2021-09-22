package com.bondidos.task5.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiData(
    //@Json(name = "list") val list: List<CatData>
    @Json(name = "id") val id: String,
    @Json(name = "url") val url: String
)
/*
@JsonClass(generateAdapter = true)
data class CatData(
    @Json(name = "id") val id: String,
    @Json(name = "picture") val picture: String
)*/
