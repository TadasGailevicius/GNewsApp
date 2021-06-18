package com.tedm.gnewsapp.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tedm.gnewsapp.R
import com.tedm.gnewsapp.other.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_error)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
    )


    /*

    @Singleton
    @Provides
    fun provideArticleDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        ArticleDatabase::class.java,
        NEWS_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideArticleDao(db: ArticleDatabase) = db.getArticleDao()

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit.Builder): NewsApi {
        return retrofit
            .build()
            .create(NewsApi::class.java)
    }


     */
}