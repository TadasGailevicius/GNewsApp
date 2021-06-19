package com.tedm.gnewsapp.di

import android.content.Context
import androidx.room.Room
import com.tedm.gnewsapp.data.local.NewsDatabase
import com.tedm.gnewsapp.data.remote.NewsApi
import com.tedm.gnewsapp.other.Constants.Companion.BASE_URL
import com.tedm.gnewsapp.other.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNewsDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, NewsDatabase::class.java , DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideNewsDao(db: NewsDatabase) = db.newsDao()

    @Singleton
    @Provides
    fun provideNewsApi() : NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }
}