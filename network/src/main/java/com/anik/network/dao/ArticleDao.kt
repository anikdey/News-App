package com.anik.network.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.anik.network.response.Article
import com.anik.network.util.Constants

@Dao
interface ArticleDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(article: Article): Long

    @Query("SELECT * FROM ${Constants.tableArticle}")
    fun getArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

}