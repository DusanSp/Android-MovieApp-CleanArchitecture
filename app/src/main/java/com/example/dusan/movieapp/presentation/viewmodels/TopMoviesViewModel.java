package com.example.dusan.movieapp.presentation.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;
import com.example.dusan.movieapp.data.entity.BaseResponse;
import com.example.dusan.movieapp.domain.data.TopMovieDomainData;
import com.example.dusan.movieapp.domain.interactor.DefaultSingleObserver;
import com.example.dusan.movieapp.domain.interactor.GetTopMoviesListUseCase;
import com.example.dusan.movieapp.domain.interactor.GetTopMoviesListUseCase.Params;
import com.example.dusan.movieapp.presentation.mapper.TopMoviesDataMapper;
import com.example.dusan.movieapp.presentation.model.Resource;
import com.example.dusan.movieapp.presentation.model.TopMovie;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TopMoviesViewModel extends AndroidViewModel {

  private GetTopMoviesListUseCase getTopMoviesListUseCaseUseCase;
  private TopMoviesDataMapper movieModelDataMapper;

  // received data
  public final MutableLiveData<Resource<List<TopMovie>>> data;
  // page number
  private final MutableLiveData<Integer> page;

  public TopMoviesViewModel(@NonNull Application application) {
    super(application);

    this.getTopMoviesListUseCaseUseCase = new GetTopMoviesListUseCase();
    this.movieModelDataMapper = new TopMoviesDataMapper();

    data = new MutableLiveData<>();
    data.setValue(Resource.loading(null));

    page = new MutableLiveData<>();

    setPageValue(1);
  }

  @Override
  protected void onCleared() {
    this.getTopMoviesListUseCaseUseCase.dispose();
    super.onCleared();
  }

  private void setPageValue(int pageValue) {
    page.setValue(pageValue);
  }

  public LiveData<Resource<List<TopMovie>>> getTopMovies() {
    return Transformations.switchMap(page, input -> {
      this.getTopMoviesListUseCaseUseCase
          .execute(new TopMoviesSingleObserver(), Params.page(input));
      return data;
    });
  }

  private void setData(Resource<List<TopMovieDomainData>> resource) {
    Collection<TopMovie> topMovieModelCollection =
        TopMoviesViewModel.this.movieModelDataMapper.transform(resource.getData());

    TopMoviesViewModel.this.data
        .setValue(Resource.success(new ArrayList<>(topMovieModelCollection)));
  }

  private final class TopMoviesSingleObserver extends
      DefaultSingleObserver<BaseResponse<TopMovieDomainData>> {

    @Override
    public void onSuccess(BaseResponse<TopMovieDomainData> topMovies) {
      if (topMovies != null && topMovies.getResults() != null) {
        TopMoviesViewModel.this.setData(Resource.success(topMovies.getResults()));
      }
    }

    @Override
    public void onError(Throwable e) {
      TopMoviesViewModel.this.setData(Resource.error(e.getMessage(), null));
    }
  }
}
