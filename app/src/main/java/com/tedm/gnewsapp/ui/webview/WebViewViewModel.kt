package com.tedm.gnewsapp.ui.webview

import androidx.lifecycle.ViewModel
import com.tedm.gnewsapp.repositories.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {
    fun observeArticleByID(postID: Int) = repository.observeArticleByID(postID)
}