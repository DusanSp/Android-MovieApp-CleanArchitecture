package com.example.dusan.movieapp.presentation.view.activity;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;
import javax.inject.Inject;

public abstract class BaseActivity<VM extends ViewModel, DB extends ViewDataBinding> extends DaggerAppCompatActivity {

  @Inject
  ViewModelProvider.Factory viewModelFactory;

  private VM viewModel;
  private DB dataBinding;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);

    setContentView(provideLayout());

    setupViewModel();

    setDataBinding();
  }

  protected abstract Class<VM> provideViewModelClass();

  protected abstract int provideLayout();

  private void setupViewModel() {
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(provideViewModelClass());
  }

  protected VM getViewModel() {
    return viewModel;
  }

  private void setDataBinding() {
    dataBinding = DataBindingUtil.setContentView(this, provideLayout());
    dataBinding.setLifecycleOwner(this);
  }

  protected DB getDataBinding() {
    return dataBinding;
  }
}
