package com.tedm.gnewsapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locally_deleted_article_ids")
data class LocallyDeletedArticleID(
    @PrimaryKey(autoGenerate = false)
    val deletedArticleID: Int
)