package com.example.dusan.movieapp.data.net;

import com.example.dusan.movieapp.BuildConfig;
import com.example.dusan.movieapp.presentation.view.application.MovieApplication;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static ApiMethods mApiMethods;

    public static ApiMethods getApi() {
        if (mApiMethods == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .header("X-Via", "Android")
                                .header("Accept", "application/json")
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    })
                    .addInterceptor(new AuthInterceptor())
                    .addInterceptor(interceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(MovieApplication.getGson()))
                    .build();
            mApiMethods = retrofit.create(ApiMethods.class);
        }
        return mApiMethods;
    }
}
