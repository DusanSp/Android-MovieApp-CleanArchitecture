package com.example.dusan.movieapp.domain.repository;

import com.example.dusan.movieapp.data.entity.BaseResponse;
import com.example.dusan.movieapp.data.entity.MovieEntity;
import com.example.dusan.movieapp.domain.data.TopMoviesDomainData;
import io.reactivex.Observable;

public interface ITopMoviesRepository {

  Observable<BaseResponse<TopMoviesDomainData>> topMovies(final int page);
}
