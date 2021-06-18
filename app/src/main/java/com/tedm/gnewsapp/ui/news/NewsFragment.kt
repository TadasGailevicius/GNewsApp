package com.tedm.gnewsapp.ui.news

import android.os.Bundle
import android.view.View
import com.tedm.gnewsapp.R
import com.tedm.gnewsapp.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment: BaseFragment(R.layout.fragment_news){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}