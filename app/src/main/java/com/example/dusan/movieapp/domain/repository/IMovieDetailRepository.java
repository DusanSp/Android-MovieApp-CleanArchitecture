package com.example.dusan.movieapp.domain.repository;

import com.example.dusan.movieapp.data.entity.MovieDetail;
import io.reactivex.Single;

public interface IMovieDetailRepository {

  Single<MovieDetail> getMovieDetail(long movieId);
}
