package com.example.dusan.movieapp.data.network;

import com.example.dusan.movieapp.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private static final String API_KEY = "api_key";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        HttpUrl.Builder httpUrlBuilder = request.url().newBuilder(request.url().toString());

        if (httpUrlBuilder != null) {
            httpUrlBuilder.addQueryParameter(API_KEY, BuildConfig.AUTH_KEY);

            request = request.newBuilder().url(httpUrlBuilder.build()).build();
        }

        return chain.proceed(request);
    }
}
