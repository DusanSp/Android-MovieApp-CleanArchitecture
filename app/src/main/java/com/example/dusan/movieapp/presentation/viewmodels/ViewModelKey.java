package com.example.dusan.movieapp.presentation.viewmodels;

import android.arch.lifecycle.ViewModel;
import dagger.MapKey;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface ViewModelKey {

  Class<? extends ViewModel> value();
}
