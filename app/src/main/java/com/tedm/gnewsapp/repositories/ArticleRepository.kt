package com.tedm.gnewsapp.repositories

import android.app.Application
import com.tedm.gnewsapp.data.local.NewsDao
import com.tedm.gnewsapp.data.local.entities.Article
import com.tedm.gnewsapp.data.remote.NewsApi
import com.tedm.gnewsapp.data.remote.entities.Articles
import com.tedm.gnewsapp.other.Resource
import com.tedm.gnewsapp.other.checkForInternetConnection
import com.tedm.gnewsapp.other.networkBoundResource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val newsDao: NewsDao,
    private val newsApi: NewsApi,
    private val context: Application
){
    suspend fun insertArticle(article: Article) {
                newsDao.insertArticle(article)
    }

    suspend fun insertArticles(articles: Articles) {
        var i = 0
        articles.articles.forEach { articles ->
            var newArticle = Article(
                i,
                articles.content,
                articles.description,
                articles.image,
                articles.publishedAt,
                articles.title,
                articles.url)
            insertArticle(newArticle)
        i++
        }
    }

    fun observeArticleByID(articleID: Int) = newsDao.observeArticleById(articleID)

    private var curNewsResponse: Response<Articles>? = null

    suspend fun syncNews() {
        curNewsResponse = newsApi.getTopHeadlines()
        curNewsResponse?.body()?.let { articles ->
            newsDao.deleteAllArticles()
            insertArticles(articles)
        }
    }

    fun getAllArticles(): Flow<Resource<List<Article>>> {
        return networkBoundResource(
            query = {
                newsDao.getAllArticles()
            },
            fetch = {
                syncNews()
                curNewsResponse
            },

            saveFetchResult = { response ->
                response?.body()?.let {
                    insertArticles(it)
                }
            },

            shouldFetch = {
                checkForInternetConnection(context)
            }
        )
    }
}