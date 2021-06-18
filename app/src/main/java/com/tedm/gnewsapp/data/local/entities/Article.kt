package com.tedm.gnewsapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tedm.gnewsapp.data.remote.entities.Source
import java.util.*

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val content: String,
    val description: String,
    val image: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String
)