package com.tedm.gnewsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tedm.gnewsapp.data.local.entities.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class NewsDatabase  : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}