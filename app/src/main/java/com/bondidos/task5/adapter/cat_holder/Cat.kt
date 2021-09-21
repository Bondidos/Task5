package com.bondidos.task5.adapter.cat_holder

data class Cat(
    val id: String,
    val content: String,
    val details: String) {

    override fun toString(): String = content
}