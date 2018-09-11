package com.example.dusan.movieapp.presentation.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.util.DiffUtil;
import android.util.Log;
import com.example.dusan.movieapp.BR;
import com.example.dusan.movieapp.R;
import com.example.dusan.movieapp.databinding.ActivityTopMoviesBinding;
import com.example.dusan.movieapp.presentation.adapters.TopMoviesAdapter;
import com.example.dusan.movieapp.presentation.binding.ActivityDataBindingComponent;
import com.example.dusan.movieapp.presentation.model.Resource;
import com.example.dusan.movieapp.presentation.model.TopMovie;
import com.example.dusan.movieapp.presentation.viewmodels.TopMoviesViewModel;
import java.util.List;

public class TopMoviesActivity extends AppCompatActivity {

  private static final String TAG = TopMoviesActivity.class.getSimpleName();

  private TopMoviesViewModel mTopMoviesViewModel;
  private ActivityTopMoviesBinding mDataBinding;
  private TopMoviesAdapter mTopMoviesAdapter;

  public static Intent createIntent(@NonNull Context context) {
    return new Intent(context, TopMoviesActivity.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_top_movies);

    setupViewModel();

    setupDataBinding();

    setupAdapter();
  }

  private void setupAdapter() {
    final DataBindingComponent dataBindingComponent = new ActivityDataBindingComponent(this);
    mTopMoviesAdapter = new TopMoviesAdapter(
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
    mDataBinding.recyclerView.setAdapter(mTopMoviesAdapter);
  }

  private void setupDataBinding() {
    mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_top_movies);
    mDataBinding.setLifecycleOwner(this);
    mDataBinding.setVariable(BR.vm, mTopMoviesViewModel);
  }

  private void setupViewModel() {
    mTopMoviesViewModel = ViewModelProviders.of(this).get(TopMoviesViewModel.class);
    mTopMoviesViewModel.getTopMovies().observe(this, listResource -> {
      if (listResource != null) {
        handleResponse(listResource);
      }
    });
  }

  @Override
  protected void onStart() {
    super.onStart();
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
    mDataBinding.setLoadingGone(false);
  }

  private void handleSuccessState(List<TopMovie> topMoviesList) {
    if (topMoviesList != null) {
      Log.d(TAG, "SUCCESS");
      mDataBinding.setLoadingGone(true);
      mTopMoviesAdapter.submitList(topMoviesList);
      for (TopMovie topMovieModel : topMoviesList) {
        Log.d(TAG, topMovieModel.getTitle());
      }
    }
  }

  private void handleErrorState(String message) {
    Log.e(TAG, message);
    mDataBinding.setLoadingGone(true);
  }
}
