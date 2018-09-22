package com.example.dusan.movieapp.presentation.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.example.dusan.movieapp.domain.interactor.DefaultSingleObserver;
import com.example.dusan.movieapp.domain.interactor.MovieDetailsUseCase;
import com.example.dusan.movieapp.domain.interactor.MovieDetailsUseCase.Params;
import com.example.dusan.movieapp.presentation.mapper.MovieDetailDataMapper;
import com.example.dusan.movieapp.presentation.model.MovieDetail;
import com.example.dusan.movieapp.presentation.model.Resource;

public class MovieDetailViewModel extends AndroidViewModel {

  private MovieDetailsUseCase movieDetailsUseCase;
  private MovieDetailDataMapper movieDetailDataMapper;
  private MutableLiveData<Resource<MovieDetail>> data;
  private long movieId;

  public MovieDetailViewModel(@NonNull Application application, long movieId) {
    super(application);

    this.movieId = movieId;
    this.movieDetailsUseCase = new MovieDetailsUseCase();
    this.movieDetailDataMapper = new MovieDetailDataMapper();
    this.data = new MutableLiveData<>();
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


  public static class Factory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Application application;
    private final long movieId;

    public Factory(@NonNull Application application, long movieId) {
      this.application = application;
      this.movieId = movieId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
      //noinspection unchecked
      return (T) new MovieDetailViewModel(application, movieId);
    }
  }
}
