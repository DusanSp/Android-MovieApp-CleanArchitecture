package com.example.dusan.movieapp.presentation.injection;

import android.app.Application;
import com.example.dusan.movieapp.BuildConfig;
import com.example.dusan.movieapp.data.network.ApiMethods;
import com.example.dusan.movieapp.data.network.AuthInterceptor;
import com.example.dusan.movieapp.data.repository.RepositoriesModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import dagger.android.DaggerApplication;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {RepositoriesModule.class})
public class AppModule {

  @Provides
  @Singleton
  public Application provideApplication(DaggerApplication application) {
    return application;
  }

  @Provides
  @Singleton
  public ApiMethods provideApiMethods(Retrofit retrofit) {
    return retrofit.create(ApiMethods.class);
  }

  @Provides
  @Singleton
  public Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
    return new Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
  }

  @Provides
  @Singleton
  public Gson provideGson() {
    return new GsonBuilder()
        .serializeNulls()
        .create();
  }

  @Provides
  @Singleton
  public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor,
      AuthInterceptor authInterceptor) {
    return new OkHttpClient.Builder()
        .addInterceptor(chain -> {
          Request original = chain.request();

          Request request = original.newBuilder()
              .header("X-Via", "Android")
              .header("Accept", "application/json")
              .method(original.method(), original.body())
              .build();

          return chain.proceed(request);
        })
        .addInterceptor(authInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build();
  }

  @Provides
  @Singleton
  public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return httpLoggingInterceptor;
  }

  @Provides
  @Singleton
  public AuthInterceptor provideAuthInterceptor() {
    return new AuthInterceptor();
  }
}
