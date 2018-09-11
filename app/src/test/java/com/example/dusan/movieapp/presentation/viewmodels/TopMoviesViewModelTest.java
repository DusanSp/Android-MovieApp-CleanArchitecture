package com.example.dusan.movieapp.presentation.viewmodels;

import android.app.Application;
import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import com.example.dusan.movieapp.domain.interactor.GetTopMoviesListUseCase;
import com.example.dusan.movieapp.presentation.model.Resource;
import com.example.dusan.movieapp.presentation.model.TopMovie;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import kotlin.jvm.JvmField;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(MockitoJUnitRunner.class)
public class TopMoviesViewModelTest {

    private TopMoviesViewModel topMoviesViewModel;

    @Rule
    @JvmField
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
    @Mock
    private Application application;
    @Mock
    private Observer<Resource<List<TopMovie>>> observer;
    @Mock
    private GetTopMoviesListUseCase getTopMoviesListUseCaseUseCase;

    @Before
    public void setUp() {
        topMoviesViewModel = new TopMoviesViewModel(application);
    }

    @Test
    public void nullDataTest() {
        assertThat(topMoviesViewModel.data, notNullValue());
    }

    @Test
    public void dataTest() {
    }
}