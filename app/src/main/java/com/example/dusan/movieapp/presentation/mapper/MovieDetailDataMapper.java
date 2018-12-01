package com.example.dusan.movieapp.presentation.mapper;

import com.example.dusan.movieapp.presentation.model.MovieDetail;
import javax.inject.Inject;

public class MovieDetailDataMapper {

  @Inject
  public MovieDetailDataMapper() {

  }

  public MovieDetail transform(
      com.example.dusan.movieapp.data.entity.MovieDetail movieDetailSource) {
    if (movieDetailSource == null) {
      throw new IllegalArgumentException("Cannot transform null value");
    }

    final MovieDetail movieDetail = new MovieDetail(movieDetailSource.getId());
    movieDetail.setBackdropPath(movieDetailSource.getBackdropPath());
    movieDetail.setTitle(movieDetailSource.getTitle());
    movieDetail.setOverview(movieDetailSource.getOverview());
    movieDetail.setVoteAverage(movieDetailSource.getVoteAverage());
    movieDetail.setVoteCount(movieDetailSource.getVoteCount());
    movieDetail.setPosterPath(movieDetailSource.getPosterPath());
    return movieDetail;
  }
}
