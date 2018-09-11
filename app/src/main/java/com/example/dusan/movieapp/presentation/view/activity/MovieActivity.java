package com.example.dusan.movieapp.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import com.example.dusan.movieapp.R;
import com.example.dusan.movieapp.presentation.model.TopMovie;

public class MovieActivity extends AppCompatActivity {

  private static final String TAG = MovieActivity.class.getSimpleName();
  private static final String MOVIE_ID_EXTRA =
      MovieActivity.class.getSimpleName() + ".MOVIE_ID_EXTRA";

  public static Intent createIntent(@NonNull Context context, TopMovie topMovie) {
    Intent intent = new Intent(context, MovieActivity.class);
    intent.putExtra(MOVIE_ID_EXTRA, topMovie.getId());
    return intent;
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_movie);

    setupToolbar();

    Intent intent = getIntent();
    if (intent != null) {
      long id = intent.getLongExtra(MOVIE_ID_EXTRA, -1);

      Log.d(TAG, "onCreate: " + id);
    }


  }

  private void setupToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
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
