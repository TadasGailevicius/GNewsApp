package com.tedm.gnewsapp.ui.home

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import com.tedm.gnewsapp.R
import com.tedm.gnewsapp.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home){

    //private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER

    }
}