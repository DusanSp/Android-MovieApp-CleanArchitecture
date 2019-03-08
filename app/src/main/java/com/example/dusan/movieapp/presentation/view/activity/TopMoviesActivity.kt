package com.example.dusan.movieapp.presentation.view.activity

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.recyclerview.extensions.AsyncDifferConfig
import android.support.v7.util.DiffUtil
import android.util.Log
import com.example.dusan.movieapp.R
import com.example.dusan.movieapp.databinding.ActivityTopMoviesBinding
import com.example.dusan.movieapp.presentation.adapters.TopMoviesAdapter
import com.example.dusan.movieapp.presentation.binding.ActivityDataBindingComponent
import com.example.dusan.movieapp.presentation.model.Resource
import com.example.dusan.movieapp.presentation.model.Status
import com.example.dusan.movieapp.presentation.model.TopMovie
import com.example.dusan.movieapp.presentation.viewmodels.TopMoviesViewModel

class TopMoviesActivity : BaseActivity<TopMoviesViewModel, ActivityTopMoviesBinding>() {
    private var topMoviesAdapter: TopMoviesAdapter? = null

    companion object {

        private val TAG = TopMoviesActivity::class.java.simpleName

        @JvmStatic
        fun createIntent(context: Context): Intent {
            return Intent(context, TopMoviesActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getData()

        setupAdapter()
    }

    override fun provideViewModelClass(): Class<TopMoviesViewModel> {
        return TopMoviesViewModel::class.java
    }

    override fun provideLayout(): Int {
        return R.layout.activity_top_movies
    }

    private fun setupAdapter() {
        val dataBindingComponent = ActivityDataBindingComponent(this)
        topMoviesAdapter = TopMoviesAdapter(
                AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<TopMovie>() {
                    override fun areItemsTheSame(oldItem: TopMovie, newItem: TopMovie): Boolean {
                        return oldItem.id == newItem.id
                    }

                    override fun areContentsTheSame(oldItem: TopMovie, newItem: TopMovie): Boolean {
                        return oldItem.id == newItem.id
                    }
                }), dataBindingComponent)
        dataBinding.recyclerView.adapter = topMoviesAdapter
    }

    private fun getData() {
        viewModel.topMovies.observe(this, Observer<Resource<List<TopMovie>>> { resource ->
            resource?.let {
                handleResponse(it)
            }
        })
    }

    private fun handleResponse(resource: Resource<List<TopMovie>>) {
        when (resource.status) {
            Status.LOADING -> {
                handleLoadingState()
            }
            Status.SUCCESS -> {
                handleSuccessState(resource.data)
            }
            Status.ERROR -> {
                handleErrorState(resource.message)
            }
        }
    }

    private fun handleLoadingState() {
        Log.d(TAG, "LOADING")
        dataBinding.loadingGone = false
    }

    private fun handleSuccessState(topMoviesList: List<TopMovie>?) {
        if (topMoviesList != null) {
            Log.d(TAG, "SUCCESS")
            dataBinding.loadingGone = true
            topMoviesAdapter?.submitList(topMoviesList)
            for (topMovieModel in topMoviesList) {
                Log.d(TAG, topMovieModel.title)
            }
        }
    }

    private fun handleErrorState(message: String?) {
        Log.e(TAG, message)
        dataBinding.loadingGone = true
    }
}
