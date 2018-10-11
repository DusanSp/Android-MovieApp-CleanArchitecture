package com.example.dusan.movieapp.data.network;

import com.example.dusan.movieapp.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

  @Singleton
  @Provides
  ApiMethods provideApiMethods(Retrofit retrofit) {
    return retrofit.create(ApiMethods.class);
  }

  @Singleton
  @Provides
  Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
    return new Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
  }

  @Singleton
  @Provides
  Gson provideGson() {
    return new GsonBuilder()
        .serializeNulls()
        .create();
  }

  @Singleton
  @Provides
  OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor,
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

  @Singleton
  @Provides
  HttpLoggingInterceptor provideHttpLoggingInterceptor() {
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return httpLoggingInterceptor;
  }

  @Singleton
  @Provides
  AuthInterceptor provideAuthInterceptor() {
    return new AuthInterceptor();
  }
}
