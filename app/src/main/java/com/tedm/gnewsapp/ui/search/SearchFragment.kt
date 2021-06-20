package com.tedm.gnewsapp.ui.search

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tedm.gnewsapp.R
import com.tedm.gnewsapp.adapters.NewsAdapter
import com.tedm.gnewsapp.other.Status
import com.tedm.gnewsapp.ui.BaseFragment
import com.tedm.gnewsapp.ui.news.NewsFragmentDirections

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_search.*

@AndroidEntryPoint
class SearchFragment : BaseFragment(R.layout.fragment_search){

    private val viewModel: SearchViewModel by viewModels()

    private lateinit var newsAdapter: NewsAdapter

    private val swipingItem = MutableLiveData(false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onViewCreated(view, savedInstanceState)
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER
        setupRecyclerView()
        setupSwipeRefreshLayout()
        subscribeToObservers()

        newsAdapter.setOnItemClickListener {
            findNavController().navigate(
                NewsFragmentDirections.actionNewsFragmentToWebViewFragment(it.id)
            )
        }

    }

    private fun subscribeToObservers() {
        var searchQuery = etSearch.text
        viewModel.allSearchArticles.observe(viewLifecycleOwner, {
            it?.let { event ->
                val result = event.peekContent()
                when(result.status){
                    Status.SUCCESS -> {
                        newsAdapter.articles = result.data!!
                        swipeRefreshLayout1.isRefreshing = false
                    }
                    Status.ERROR -> {
                        event.getContentIfNotHandled()?.let { errorResource ->
                            errorResource.message?.let { message ->
                                showSnackbar(message)
                            }
                        }

                        result.data?.let { articles ->
                            newsAdapter.articles = articles
                        }

                        swipeRefreshLayout1.isRefreshing = false
                    }

                    Status.LOADING -> {
                        result.data?.let { articles ->
                            newsAdapter.articles = articles
                        }
                        swipeRefreshLayout1.isRefreshing = true
                    }
                }
            }
        })

        swipingItem.observe(viewLifecycleOwner, {
            swipeRefreshLayout1.isEnabled = !it
        })
    }

    private fun setupSwipeRefreshLayout() {
        swipeRefreshLayout1.setOnRefreshListener {
            viewModel.synchAllSearchArticles()
        }
    }

    private fun setupRecyclerView() = rvSearchArticles.apply {
        newsAdapter = NewsAdapter()
        adapter = newsAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

}