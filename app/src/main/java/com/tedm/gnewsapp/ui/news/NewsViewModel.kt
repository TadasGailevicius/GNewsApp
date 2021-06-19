package com.tedm.gnewsapp.ui.news

import androidx.lifecycle.*
import com.tedm.gnewsapp.data.local.entities.Article
import com.tedm.gnewsapp.other.Event
import com.tedm.gnewsapp.other.Resource
import com.tedm.gnewsapp.repositories.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel  @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    private val _forceUpdate = MutableLiveData(false)

    private val _allArticles = _forceUpdate.switchMap {
        repository.getAllArticles().asLiveData(viewModelScope.coroutineContext)
    }.switchMap {
        MutableLiveData(Event(it))
    }

    val allArticles: LiveData<Event<Resource<List<Article>>>> = _allArticles

    fun synchAllArticles() = _forceUpdate.postValue(true)
}