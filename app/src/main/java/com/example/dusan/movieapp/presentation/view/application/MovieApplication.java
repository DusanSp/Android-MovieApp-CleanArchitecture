package com.example.dusan.movieapp.presentation.view.application;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.example.dusan.movieapp.presentation.injection.AppComponent;
import com.example.dusan.movieapp.presentation.injection.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MovieApplication extends DaggerApplication {

  private static MovieApplication sInstance;

  public static Application getApplication() {
    return sInstance;
  }

  @Override
  public void onCreate() {
    super.onCreate();

    sInstance = this;
  }

  @Override
  protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
    appComponent.inject(this);
    return appComponent;
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
