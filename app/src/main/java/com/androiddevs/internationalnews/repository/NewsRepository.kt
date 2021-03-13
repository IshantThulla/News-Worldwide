package com.androiddevs.internationalnews.repository

import com.androiddevs.internationalnews.API.RetrofitInstance
import com.androiddevs.internationalnews.Article
import com.androiddevs.internationalnews.Database.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
){
    suspend fun getTopHeadlines(country: String, pageNumber: Int) =
        RetrofitInstance.api.getTopHeadlines(country, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchNews1(searchQuery, pageNumber)

    suspend fun insert(article: Article) = db.getArticleDao().insert(article)

    fun getArticles() = db.getArticleDao().getArticles()

    suspend fun delete(article: Article) = db.getArticleDao().deleteArticle(article)
}