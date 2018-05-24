package com.example.dusan.movieapp.data.net;

import com.example.dusan.movieapp.data.entity.BaseResponse;
import com.example.dusan.movieapp.data.entity.MovieEntity;
import com.example.dusan.movieapp.domain.data.TopMoviesDomainData;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiMethods {

  @GET("movie/top_rated")
  Observable<BaseResponse<MovieEntity>> getTopRatedMovies(@Query("page") int pageNumber);
}
