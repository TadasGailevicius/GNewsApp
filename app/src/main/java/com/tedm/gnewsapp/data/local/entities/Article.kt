package com.tedm.gnewsapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val content: String,
    val description: String,
    val image: String,
    val publishedAt: String,
    val title: String,
    val url: String
)