package com.example.dusan.movieapp.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.example.dusan.movieapp.presentation.view.activity.TopMoviesActivity;

public class Navigator {

    private static Navigator instance = null;

    public static Navigator getInstance() {
        if (instance == null) {
            instance = new Navigator();
        }

        return instance;
    }

    public void navigateToTopicMoviesActivity(Context context) {
        if (context != null) {
            Intent topMoviesIntent = TopMoviesActivity.getCallingIntent(context);
            context.startActivity(topMoviesIntent);
        }
    }
}
