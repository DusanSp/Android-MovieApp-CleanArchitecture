package com.example.dusan.movieapp.data.network;

import com.example.dusan.movieapp.data.entity.BaseResponse;
import com.example.dusan.movieapp.data.entity.MovieDetail;
import com.example.dusan.movieapp.data.entity.TopMovieEntity;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiMethods {

  @GET("movie/top_rated")
  Single<BaseResponse<TopMovieEntity>> getTopRatedMovies(@Query("page") int pageNumber);

  @GET("movie/{movie_id}")
  Single<MovieDetail> getMovie(@Path("movie_id") long movieId);
}
