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

    final MovieDetail movieDetail1 = new MovieDetail(movieDetailSource.getId());
    movieDetail1.setPosterPath(movieDetailSource.getBackdropPath());
    movieDetail1.setTitle(movieDetailSource.getTitle());
    return movieDetail1;
  }
}
