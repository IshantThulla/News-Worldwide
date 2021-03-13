package com.androiddevs.internationalnews.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androiddevs.internationalnews.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}