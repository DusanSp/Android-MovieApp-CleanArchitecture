package com.example.dusan.movieapp.presentation.binding;

import android.app.Activity;

public class ActivityDataBindingComponent implements android.databinding.DataBindingComponent {

    private ActivityBindingAdapters activityBindingAdapters;

    public ActivityDataBindingComponent(Activity activity) {
        activityBindingAdapters = new ActivityBindingAdapters(activity);
    }

    public ActivityBindingAdapters getActivityBindingAdapters() {
        return activityBindingAdapters;
    }
}
