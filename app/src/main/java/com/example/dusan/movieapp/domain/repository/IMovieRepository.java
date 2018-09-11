package com.example.dusan.movieapp.domain.repository;

import io.reactivex.Single;

public interface IMovieRepository {

  Single<String> getMovie(long movieId);
}
