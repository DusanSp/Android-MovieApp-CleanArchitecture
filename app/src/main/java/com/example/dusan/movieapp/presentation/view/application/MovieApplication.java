package com.example.dusan.movieapp.presentation.view.application;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MovieApplication extends Application {

  private static MovieApplication sInstance;
  private static Gson sGson;

  public static Application getApplication() {
    return sInstance;
  }

  @Override
  public void onCreate() {
    super.onCreate();

    sInstance = this;

    iniGson();
  }

  public static Gson getGson() {
    return sGson;
  }

  private void iniGson() {
    sGson = new GsonBuilder()
        .serializeNulls()
        .create();
  }

  public static boolean haveInternetConnection() {
    ConnectivityManager cm =
        (ConnectivityManager) getApplication().getApplicationContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE);

    if (cm == null) {
      return false;
    }
    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
  }
}
