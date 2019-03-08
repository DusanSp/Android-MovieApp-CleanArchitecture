package com.example.dusan.movieapp.presentation.viewmodels;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.dusan.movieapp.domain.interactor.TopMoviesUseCase;
import com.example.dusan.movieapp.presentation.mapper.TopMoviesDataMapper;
import com.example.dusan.movieapp.presentation.model.Status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.DisposableSingleObserver;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TopMoviesViewModelTest {

    private TopMoviesViewModel topMoviesViewModel;
    @Mock
    private TopMoviesUseCase topMoviesUseCase;
    @Mock
    private TopMoviesDataMapper topMoviesDataMapper;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        topMoviesViewModel = Mockito.spy(new TopMoviesViewModel(topMoviesUseCase, topMoviesDataMapper));
    }

    @Test
    public void testNull() {
        assertThat(topMoviesViewModel.getTopMoviesUseCase(), notNullValue());
        assertThat(topMoviesViewModel.getMovieModelDataMapper(), notNullValue());
        verify(topMoviesUseCase, never()).execute(any(), any());
    }

    @Test
    public void dontFetchWithOutObservers() {
        verify(topMoviesUseCase, never()).execute(any(), any());
    }

    @Test
    public void fetchWithOutObservers() {
        verify(topMoviesUseCase).execute(any(DisposableSingleObserver.class), any(TopMoviesUseCase.Companion.Params.class));
    }

    @Test
    public void nullCheckTopMovies() {
        assertNotNull(topMoviesViewModel.getTopMovies());
    }

    @Test
    public void checkDataInDataParam() {
        assertNotNull(topMoviesViewModel.getData().getValue());
        assertEquals(topMoviesViewModel.getData().getValue().getStatus(), Status.LOADING);
    }
}
