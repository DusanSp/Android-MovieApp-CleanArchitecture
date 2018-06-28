package com.example.dusan.movieapp.presentation.mapper;

import com.example.dusan.movieapp.domain.data.TopMovieDomainData;
import com.example.dusan.movieapp.presentation.model.TopMovie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TopMoviesDataMapper {

    public TopMovie transform(TopMovieDomainData topMovies) {
        if (topMovies == null) {
            throw new IllegalArgumentException("Cannot transform null value");
        }

        final TopMovie topMovieModel = new TopMovie(topMovies.getId());
        topMovieModel.setPosterPath(topMovies.getPosterPath());
        topMovieModel.setReleaseDate(topMovies.getReleaseDate());
        topMovieModel.setTitle(topMovies.getTitle());
        topMovieModel.setVoteAverage(topMovies.getVoteAverage());

        return topMovieModel;
    }

    public Collection<TopMovie> transform(Collection<TopMovieDomainData> topMoviesCollection) {
        Collection<TopMovie> topMovieModelsCollection;

        if (topMoviesCollection != null && !topMoviesCollection.isEmpty()) {
            topMovieModelsCollection = new ArrayList<>();

            for (TopMovieDomainData topMovie : topMoviesCollection) {
                topMovieModelsCollection.add(transform(topMovie));
            }
        } else {
            topMovieModelsCollection = Collections.emptyList();
        }

        return topMovieModelsCollection;
    }
}
