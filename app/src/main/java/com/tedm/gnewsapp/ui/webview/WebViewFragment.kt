package com.tedm.gnewsapp.ui.webview

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tedm.gnewsapp.R
import com.tedm.gnewsapp.data.local.entities.Article
import com.tedm.gnewsapp.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_webview.*

@AndroidEntryPoint
class WebViewFragment : BaseFragment(R.layout.fragment_webview){

    private val viewModel: WebViewViewModel by viewModels()

    private val args: WebViewFragmentArgs by navArgs()

    private var curArticle: Article? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        viewModel.observeArticleByID(args.id).observe(viewLifecycleOwner, {
            it?.let { article ->
                val myWebView: WebView = article_web_view
                myWebView.loadUrl(article.url)
                curArticle = article
            } ?: showSnackbar("Article not found")
        })
    }
}