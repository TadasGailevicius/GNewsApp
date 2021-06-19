package com.tedm.gnewsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tedm.gnewsapp.data.local.entities.Article
import com.tedm.gnewsapp.data.local.entities.LocallyDeletedArticleID

@Database(
    entities = [Article::class, LocallyDeletedArticleID::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class NewsDatabase  : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}