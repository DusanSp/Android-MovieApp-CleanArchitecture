package com.example.dusan.movieapp.domain.mapper;

import com.example.dusan.movieapp.data.entity.MovieEntity;
import com.example.dusan.movieapp.domain.data.TopMoviesDomainData;
import com.example.dusan.movieapp.presentation.model.TopMovie;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TopMoviesDomainDataMapper {

  public TopMoviesDomainData transform(MovieEntity movieEntity) {
    if(movieEntity == null) {
      throw new IllegalArgumentException("Cannot transform null value");
    }

    final TopMoviesDomainData topMoviesDomainData = new TopMoviesDomainData(movieEntity.getId());

    topMoviesDomainData.setAdult(movieEntity.getAdult());
    topMoviesDomainData.setBackdropPath(movieEntity.getBackdropPath());
    topMoviesDomainData.setGenreIds(movieEntity.getGenreIds());
    topMoviesDomainData.setOriginalLanguage(movieEntity.getOriginalLanguage());
    topMoviesDomainData.setOriginalTitle(movieEntity.getOriginalTitle());
    topMoviesDomainData.setOverview(movieEntity.getOverview());
    topMoviesDomainData.setPopularity(movieEntity.getPopularity());
    topMoviesDomainData.setPosterPath(movieEntity.getPosterPath());
    topMoviesDomainData.setReleaseDate(movieEntity.getReleaseDate());
    topMoviesDomainData.setTitle(movieEntity.getTitle());
    topMoviesDomainData.setVideo(movieEntity.getVideo());
    topMoviesDomainData.setVoteAverage(movieEntity.getVoteAverage());
    topMoviesDomainData.setVoteCount(movieEntity.getVoteCount());

    return topMoviesDomainData;
  }

  public Collection<TopMoviesDomainData> transform(Collection<MovieEntity> topMoviesCollection) {
    Collection<TopMoviesDomainData> topMovieModelsCollection;

    if(topMoviesCollection != null && !topMoviesCollection.isEmpty()) {
      topMovieModelsCollection = new ArrayList<>();

      for(MovieEntity movieEntity : topMoviesCollection) {
        topMovieModelsCollection.add(transform(movieEntity));
      }
    } else {
      topMovieModelsCollection = Collections.emptyList();
    }

    return topMovieModelsCollection;
  }
}
