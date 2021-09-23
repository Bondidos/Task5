package com.bondidos.task5.adapter.cat_holder

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cat(
    @Json(name = "id") val id: String,
    @Json(name = "url") val picture: String
    )