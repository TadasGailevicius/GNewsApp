package com.tedm.gnewsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tedm.gnewsapp.data.local.entities.Article
import com.tedm.gnewsapp.data.local.entities.LocallyDeletedArticleID
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<Article>>

    @Query("DELETE FROM articles")
    suspend fun deleteAllArticles()

    @Query("SELECT * FROM locally_deleted_article_ids")
    suspend fun getAllLocallyDeletedArticleIDs(): List<LocallyDeletedArticleID>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)




}