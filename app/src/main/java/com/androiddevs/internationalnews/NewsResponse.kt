package com.androiddevs.internationalnews

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)