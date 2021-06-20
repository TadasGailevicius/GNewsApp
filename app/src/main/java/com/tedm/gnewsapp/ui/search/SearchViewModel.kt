package com.tedm.gnewsapp.ui.search

import androidx.lifecycle.*
import com.tedm.gnewsapp.data.local.entities.Article
import com.tedm.gnewsapp.other.Event
import com.tedm.gnewsapp.other.Resource

import com.tedm.gnewsapp.repositories.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    private val _forceUpdate = MutableLiveData(false)

    private val _allSearchArticles = _forceUpdate.switchMap {
        repository.getAllSearchArticles().asLiveData(viewModelScope.coroutineContext)
    }.switchMap {
        MutableLiveData(Event(it))
    }

    val allSearchArticles: LiveData<Event<Resource<List<Article>>>> = _allSearchArticles

    fun synchAllSearchArticles() = _forceUpdate.postValue(true)

}