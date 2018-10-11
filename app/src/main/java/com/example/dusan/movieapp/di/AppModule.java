package com.example.dusan.movieapp.di;

import android.content.Context;
import com.example.dusan.movieapp.data.DataModule;
import com.example.dusan.movieapp.presentation.view.application.MovieApplication;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(includes = {DataModule.class})
public class AppModule {

  @Provides
  @Singleton
  Context provideAppContext(MovieApplication application) {
    return application.getApplicationContext();
  }
}
