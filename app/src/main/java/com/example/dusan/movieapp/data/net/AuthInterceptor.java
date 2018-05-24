package com.example.dusan.movieapp.data.net;

import com.example.dusan.movieapp.BuildConfig;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();

    HttpUrl.Builder httpUrlBuilder = request.url().newBuilder(request.url().toString());

    if (httpUrlBuilder != null) {
      httpUrlBuilder.addQueryParameter("api_key", BuildConfig.AUTH_KEY);

      request = request.newBuilder().url(httpUrlBuilder.build()).build();
    }

    return chain.proceed(request);
  }
}
