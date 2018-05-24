package com.example.dusan.movieapp.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.example.dusan.movieapp.presentation.navigation.Navigator;

public abstract class BaseActivity extends AppCompatActivity {

  Navigator navigator;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    navigator = Navigator.getInstance();
  }
}
