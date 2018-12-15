package com.example.dusan.movieapp.presentation.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.dusan.movieapp.data.entity.BaseResponse
import com.example.dusan.movieapp.domain.data.TopMovieDomainData
import com.example.dusan.movieapp.domain.interactor.DefaultSingleObserver
import com.example.dusan.movieapp.domain.interactor.TopMoviesUseCase
import com.example.dusan.movieapp.presentation.mapper.TopMoviesDataMapper
import com.example.dusan.movieapp.presentation.model.Resource
import com.example.dusan.movieapp.presentation.model.TopMovie
import javax.inject.Inject

class TopMoviesViewModel
@Inject constructor(private val topMoviesUseCase: TopMoviesUseCase,
                    private val movieModelDataMapper: TopMoviesDataMapper) : ViewModel() {

    // received data
    var data: MutableLiveData<Resource<List<TopMovie>>> = MutableLiveData()
    // page number
    var page: MutableLiveData<Int> = MutableLiveData()

    val topMovies: LiveData<Resource<List<TopMovie>>>
        get() = Transformations.switchMap(page) { input ->
            topMoviesUseCase.execute(TopMoviesSingleObserver(), TopMoviesUseCase.page(input))
            data
        }

    init {
        data.value = Resource.loading(null)
        setPageValue(1)
    }

    override fun onCleared() {
        this.topMoviesUseCase.dispose()
        super.onCleared()
    }

    private fun setPageValue(value: Int) {
        page.value = value
    }

    private fun setData(resource: Resource<List<TopMovie>>) {
        data.value = resource
    }

    private inner class TopMoviesSingleObserver : DefaultSingleObserver<BaseResponse<TopMovieDomainData>>() {
        override fun onSuccess(topMovies: BaseResponse<TopMovieDomainData>) {
            setData(Resource.success<List<TopMovie>>(ArrayList(movieModelDataMapper.transform(topMovies.results)!!)))
        }

        override fun onError(e: Throwable) {
            setData(Resource.error(e.message, null))
        }
    }
}

