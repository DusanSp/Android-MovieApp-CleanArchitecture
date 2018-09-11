package com.example.dusan.movieapp.data.net;

import com.example.dusan.movieapp.data.entity.BaseResponse;
import com.example.dusan.movieapp.data.entity.MovieEntity;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiMethods {

  @GET("/movie/top_rated")
  Single<BaseResponse<MovieEntity>> getTopRatedMovies(@Query("page") int pageNumber);

  @GET("/movie/{movie_id}")
  Single<String> getMovie(@Path("movie_id") long movieId);
}
