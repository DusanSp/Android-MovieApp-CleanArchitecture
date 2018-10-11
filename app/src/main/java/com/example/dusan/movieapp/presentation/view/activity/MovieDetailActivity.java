package com.example.dusan.movieapp.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.example.dusan.movieapp.R;
import com.example.dusan.movieapp.databinding.ActivityMovieDetailBinding;
import com.example.dusan.movieapp.presentation.model.MovieDetail;
import com.example.dusan.movieapp.presentation.model.Resource;
import com.example.dusan.movieapp.presentation.model.TopMovie;
import com.example.dusan.movieapp.presentation.viewmodels.MovieDetailViewModel;

public class MovieDetailActivity extends
    BaseActivity<MovieDetailViewModel, ActivityMovieDetailBinding> {

  private static final String TAG = MovieDetailActivity.class.getSimpleName();
  private static final String MOVIE_ID_EXTRA =
      MovieDetailActivity.class.getSimpleName() + ".MOVIE_ID_EXTRA";

  public static Intent createIntent(@NonNull Context context, TopMovie topMovie) {
    Intent intent = new Intent(context, MovieDetailActivity.class);
    intent.putExtra(MOVIE_ID_EXTRA, topMovie.getId());
    return intent;
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setupToolbar();

    Intent intent = getIntent();
    if (intent != null) {
      long id = intent.getLongExtra(MOVIE_ID_EXTRA, -1);
      getViewModel().setMovieId(id);
    }

    getData();
  }

  @Override
  protected Class<MovieDetailViewModel> provideViewModelClass() {
    return MovieDetailViewModel.class;
  }

  @Override
  protected int provideLayout() {
    return R.layout.activity_movie_detail;
  }

  @Override
  protected void onResume() {
    super.onResume();

    getViewModel().execute();
  }

  private void getData() {
    getViewModel().getData().observe(this, movieDetailResource -> {
      if (movieDetailResource != null) {
        handleResponse(movieDetailResource);
      }
    });
  }

  private void handleResponse(Resource<MovieDetail> resource) {
    switch (resource.getStatus()) {
      case SUCCESS:
        handleSuccessState(resource.getData());
        break;
      case LOADING:
        handleLoadingState();
        break;
      case ERROR:
        handleErrorState();
        break;
      default:
        throw new IllegalArgumentException("Invalid data");
    }
  }

  private void handleSuccessState(MovieDetail movieDetail) {
    if (movieDetail != null) {
      getDataBinding().setMovie(movieDetail);
      String title = movieDetail.getTitle();
      getDataBinding().collapsingToolbarLayout.setTitle(title);
    }
  }

  private void handleLoadingState() {

  }

  private void handleErrorState() {

  }

  private void setupToolbar() {
    Toolbar toolbar = getDataBinding().toolbar;
    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home: {
        finish();
        return true;
      }
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
