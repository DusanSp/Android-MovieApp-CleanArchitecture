package com.example.dusan.movieapp.di;

import com.example.dusan.movieapp.presentation.view.application.MovieApplication;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;
import javax.inject.Singleton;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
    AppModule.class,
    ActivityBuilderModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder application(MovieApplication application);

    AppComponent build();
  }
}
