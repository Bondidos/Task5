package com.bondidos.task5.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cat(
    @Json(name = "id") val id: String,
    @Json(name = "url") val url: String,
    @Json(name = "breeds") val breeds: List<Breeds>
    )

@JsonClass(generateAdapter = true)
data class Breeds(
    @Json(name = "description") val description: String?
)
