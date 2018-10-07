package com.example.dusan.movieapp.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingComponent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.util.DiffUtil;
import android.util.Log;
import com.example.dusan.movieapp.R;
import com.example.dusan.movieapp.databinding.ActivityTopMoviesBinding;
import com.example.dusan.movieapp.presentation.adapters.TopMoviesAdapter;
import com.example.dusan.movieapp.presentation.binding.ActivityDataBindingComponent;
import com.example.dusan.movieapp.presentation.model.Resource;
import com.example.dusan.movieapp.presentation.model.TopMovie;
import com.example.dusan.movieapp.presentation.viewmodels.TopMoviesViewModel;
import java.util.List;

public class TopMoviesActivity extends BaseActivity<TopMoviesViewModel, ActivityTopMoviesBinding> {

  private static final String TAG = TopMoviesActivity.class.getSimpleName();
  private TopMoviesAdapter topMoviesAdapter;

  public static Intent createIntent(@NonNull Context context) {
    return new Intent(context, TopMoviesActivity.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getData();

    setupAdapter();
  }

  @Override
  protected Class<TopMoviesViewModel> provideViewModelClass() {
    return TopMoviesViewModel.class;
  }

  @Override
  protected int provideLayout() {
    return R.layout.activity_top_movies;
  }

  private void setupAdapter() {
    final DataBindingComponent dataBindingComponent = new ActivityDataBindingComponent(this);
    topMoviesAdapter = new TopMoviesAdapter(
        new AsyncDifferConfig.Builder<>(new DiffUtil.ItemCallback<TopMovie>() {
          @Override
          public boolean areItemsTheSame(TopMovie oldItem, TopMovie newItem) {
            return oldItem.getId() == newItem.getId();
          }

          @Override
          public boolean areContentsTheSame(TopMovie oldItem, TopMovie newItem) {
            return oldItem.getId() == newItem.getId();
          }
        }), dataBindingComponent);
    getDataBinding().recyclerView.setAdapter(topMoviesAdapter);
  }

  private void getData() {
    getViewModel().getTopMovies().observe(this, listResource -> {
      if (listResource != null) {
        handleResponse(listResource);
      }
    });
  }

  private void handleResponse(Resource<List<TopMovie>> resource) {
    switch (resource.getStatus()) {
      case LOADING: {
        handleLoadingState();
        break;
      }
      case SUCCESS: {
        handleSuccessState(resource.getData());
        break;
      }
      case ERROR: {
        handleErrorState(resource.getMessage());
        break;
      }
    }
  }

  private void handleLoadingState() {
    Log.d(TAG, "LOADING");
    getDataBinding().setLoadingGone(false);
  }

  private void handleSuccessState(List<TopMovie> topMoviesList) {
    if (topMoviesList != null) {
      Log.d(TAG, "SUCCESS");
      getDataBinding().setLoadingGone(true);
      topMoviesAdapter.submitList(topMoviesList);
      for (TopMovie topMovieModel : topMoviesList) {
        Log.d(TAG, topMovieModel.getTitle());
      }
    }
  }

  private void handleErrorState(String message) {
    Log.e(TAG, message);
    getDataBinding().setLoadingGone(true);
  }
}
