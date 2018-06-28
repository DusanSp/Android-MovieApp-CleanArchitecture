package com.example.dusan.movieapp.domain.mapper;

import com.example.dusan.movieapp.data.entity.MovieEntity;
import com.example.dusan.movieapp.domain.data.TopMovieDomainData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TopMoviesDomainDataMapper {

    public TopMovieDomainData transform(MovieEntity movieEntity) {
        if (movieEntity == null) {
            throw new IllegalArgumentException("Cannot transform null value");
        }

        final TopMovieDomainData topMovieDomainData = new TopMovieDomainData(movieEntity.getId());

        topMovieDomainData.setAdult(movieEntity.getAdult());
        topMovieDomainData.setBackdropPath(movieEntity.getBackdropPath());
        topMovieDomainData.setGenreIds(movieEntity.getGenreIds());
        topMovieDomainData.setOriginalLanguage(movieEntity.getOriginalLanguage());
        topMovieDomainData.setOriginalTitle(movieEntity.getOriginalTitle());
        topMovieDomainData.setOverview(movieEntity.getOverview());
        topMovieDomainData.setPopularity(movieEntity.getPopularity());
        topMovieDomainData.setPosterPath(movieEntity.getPosterPath());
        topMovieDomainData.setReleaseDate(movieEntity.getReleaseDate());
        topMovieDomainData.setTitle(movieEntity.getTitle());
        topMovieDomainData.setVideo(movieEntity.getVideo());
        topMovieDomainData.setVoteAverage(movieEntity.getVoteAverage());
        topMovieDomainData.setVoteCount(movieEntity.getVoteCount());

        return topMovieDomainData;
    }

    public Collection<TopMovieDomainData> transform(Collection<MovieEntity> topMoviesCollection) {
        Collection<TopMovieDomainData> topMovieModelsCollection;

        if (topMoviesCollection != null && !topMoviesCollection.isEmpty()) {
            topMovieModelsCollection = new ArrayList<>();

            for (MovieEntity movieEntity : topMoviesCollection) {
                topMovieModelsCollection.add(transform(movieEntity));
            }
        } else {
            topMovieModelsCollection = Collections.emptyList();
        }

        return topMovieModelsCollection;
    }
}
