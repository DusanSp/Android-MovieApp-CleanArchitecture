package com.example.dusan.movieapp.presentation.view.activity

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.example.dusan.movieapp.R
import com.example.dusan.movieapp.databinding.ActivityMovieDetailBinding
import com.example.dusan.movieapp.presentation.model.MovieDetail
import com.example.dusan.movieapp.presentation.model.Resource
import com.example.dusan.movieapp.presentation.model.Status
import com.example.dusan.movieapp.presentation.viewmodels.MovieDetailViewModel

class MovieDetailActivity : BaseActivity<MovieDetailViewModel, ActivityMovieDetailBinding>() {

    companion object {
        const val MOVIE_ID_EXTRA = "MOVIE_ID_EXTRA"

        @JvmStatic
        fun createIntent(context: Context, movieId: Long?): Intent {
            return Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(MOVIE_ID_EXTRA, movieId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToolbar()

        intent?.let {
            viewModel.setMovieId(intent.getLongExtra(MOVIE_ID_EXTRA, -1))
        }

        observeData()
    }

    override fun onResume() {
        super.onResume()

        viewModel.execute()
    }

    override fun provideViewModelClass(): Class<MovieDetailViewModel> {
        return MovieDetailViewModel::class.java
    }

    override fun provideLayout(): Int {
        return R.layout.activity_movie_detail
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupToolbar() {
        val toolbar = dataBinding.toolbar

        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun observeData() {
        viewModel.data.observe(this, Observer { resource ->
            resource?.let { handleResponse(it) }
        })
    }

    private fun handleResponse(response: Resource<MovieDetail>) {
        when (response.status) {
            Status.LOADING -> TODO()
            Status.SUCCESS -> handleSuccessState(response.data)
            Status.ERROR -> TODO()
        }
    }

    private fun handleSuccessState(movieDetail: MovieDetail?) {
        movieDetail?.let {
            dataBinding.movie = movieDetail
            dataBinding.collapsingToolbarLayout.title = movieDetail.title
        }
    }
}