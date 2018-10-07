package com.example.dusan.movieapp.presentation.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.dusan.movieapp.domain.interactor.DefaultSingleObserver;
import com.example.dusan.movieapp.domain.interactor.MovieDetailsUseCase;
import com.example.dusan.movieapp.domain.interactor.MovieDetailsUseCase.Params;
import com.example.dusan.movieapp.presentation.mapper.MovieDetailDataMapper;
import com.example.dusan.movieapp.presentation.model.MovieDetail;
import com.example.dusan.movieapp.presentation.model.Resource;
import javax.inject.Inject;

public class MovieDetailViewModel extends ViewModel {

  private final MovieDetailsUseCase movieDetailsUseCase;
  private final MovieDetailDataMapper movieDetailDataMapper;
  private MutableLiveData<Resource<MovieDetail>> data;
  private long movieId;

  @Inject
  public MovieDetailViewModel(MovieDetailsUseCase movieDetailsUseCase,
      MovieDetailDataMapper movieDetailDataMapper) {
    this.movieDetailsUseCase = movieDetailsUseCase;
    this.movieDetailDataMapper = movieDetailDataMapper;
    this.data = new MutableLiveData<>();
  }

  public void setMovieId(long movieId) {
    this.movieId = movieId;
  }

  private void setData(Resource<com.example.dusan.movieapp.data.entity.MovieDetail> resource) {
    final MovieDetail movieDetail = movieDetailDataMapper.transform(resource.getData());
    this.data.setValue(Resource.success(movieDetail));
  }

  public void execute() {
    if (movieId == -1) {
      throw new IllegalArgumentException("Invalid movie id");
    }

    movieDetailsUseCase.execute(new MovieDetailSingleObserver(), Params.movieId(movieId));
  }

  @Override
  protected void onCleared() {
    this.movieDetailsUseCase.dispose();
    super.onCleared();
  }

  public LiveData<Resource<MovieDetail>> getData() {
    return data;
  }

  private final class MovieDetailSingleObserver extends
      DefaultSingleObserver<com.example.dusan.movieapp.data.entity.MovieDetail> {

    @Override
    public void onSuccess(com.example.dusan.movieapp.data.entity.MovieDetail movieDetail) {
      if (movieDetail != null) {
        MovieDetailViewModel.this.setData(Resource.success(movieDetail));
      }
    }

    @Override
    public void onError(Throwable e) {
      MovieDetailViewModel.this.setData(Resource.error(e.getMessage(), null));
    }
  }
}
