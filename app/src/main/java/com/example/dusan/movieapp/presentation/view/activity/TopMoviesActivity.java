package com.example.dusan.movieapp.presentation.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.example.dusan.movieapp.R;
import com.example.dusan.movieapp.presentation.model.Resource;
import com.example.dusan.movieapp.presentation.model.TopMovie;
import com.example.dusan.movieapp.presentation.viewmodels.TopMoviesViewModel;
import java.util.List;

public class TopMoviesActivity extends BaseActivity {

  private static final String TAG = TopMoviesActivity.class.getSimpleName();

  private TopMoviesViewModel mTopMoviesViewModel;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, TopMoviesActivity.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    mTopMoviesViewModel = ViewModelProviders.of(this).get(TopMoviesViewModel.class);
  }

  @Override
  protected void onStart() {
    super.onStart();

    mTopMoviesViewModel.getData().observe(this, resource -> {
      if (resource != null) {
        handleResponse(resource);
      }
    });
  }

  private void handleResponse(Resource<List<TopMovie>> resource) {
    switch (resource.getState()) {
      case LOADING: {
        handleLoadingState();
        break;
      }
      case SUCCESS: {
        handleSuccessState(resource.getData());
        break;
      }
      case ERROR: {
        handleErrorState(resource.getError());
        break;
      }
    }
  }

  private void handleLoadingState() {
    Log.d(TAG, "LOADING");
  }

  private void handleSuccessState(List<TopMovie> topMoviesList) {
    if(topMoviesList != null) {
      Log.d(TAG, "SUCCESS");
      for(TopMovie topMovieModel : topMoviesList) {
        Log.d(TAG, topMovieModel.getTitle());
      }
    }
  }

  private void handleErrorState(Throwable throwable) {
    Log.d(TAG, throwable.toString());
  }
}
