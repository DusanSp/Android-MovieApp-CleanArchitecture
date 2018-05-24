package com.example.dusan.movieapp.presentation.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.example.dusan.movieapp.data.entity.BaseResponse;
import com.example.dusan.movieapp.domain.data.TopMoviesDomainData;
import com.example.dusan.movieapp.domain.interactor.DefaultObserver;
import com.example.dusan.movieapp.domain.interactor.GetTopMoviesList;
import com.example.dusan.movieapp.domain.interactor.GetTopMoviesList.Params;
import com.example.dusan.movieapp.presentation.mapper.TopMovieDataMapper;
import com.example.dusan.movieapp.presentation.model.Resource;
import com.example.dusan.movieapp.presentation.model.ResourceState;
import com.example.dusan.movieapp.presentation.model.TopMovie;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TopMoviesViewModel extends AndroidViewModel {

  private GetTopMoviesList getTopMoviesListUseCase;
  private TopMovieDataMapper movieModelDataMapper;

  public final MutableLiveData<Resource<List<TopMovie>>> data;

  public TopMoviesViewModel(@NonNull Application application) {
    super(application);

    this.getTopMoviesListUseCase = new GetTopMoviesList();
    this.movieModelDataMapper = new TopMovieDataMapper();

    data = new MutableLiveData<>();

    getTopMovies(1);
  }

  public void getTopMovies(int page) {
    this.data.postValue(new Resource<>(ResourceState.LOADING, null, null));
    this.getTopMoviesListUseCase.execute(new TopMoviesObserver(), Params.page(page));
  }

  public void setData(Resource<List<TopMoviesDomainData>> resource) {
      Collection<TopMovie> topMovieModelCollection =
          TopMoviesViewModel.this.movieModelDataMapper.transform(resource.getData());

      TopMoviesViewModel.this.data.postValue(new Resource<>(resource.getState(),
          new ArrayList<>(topMovieModelCollection),
          null));
  }

  public LiveData<Resource<List<TopMovie>>> getData() {
    return data;
  }

  private final class TopMoviesObserver extends DefaultObserver<BaseResponse<TopMoviesDomainData>> {

    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(BaseResponse<TopMoviesDomainData> topMovies) {
      if (topMovies != null && topMovies.getResults() != null) {
        TopMoviesViewModel.this.setData(new Resource<>(ResourceState.SUCCESS, topMovies.getResults(), null));
      }
    }

    @Override
    public void onError(Throwable e) {

    }
  }
}
