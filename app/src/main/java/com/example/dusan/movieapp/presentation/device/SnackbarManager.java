package com.example.dusan.movieapp.presentation.device;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;

public class SnackbarManager {

    public static void showMessage(@NonNull View view,
                                   @NonNull String message) {
        showMessage(view, message, null, null);
    }

    public static void showMessage(@NonNull View view,
                                   @NonNull String message,
                                   @Nullable String actionTitle,
                                   @Nullable View.OnClickListener onClickListener) {

        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);

        if (!TextUtils.isEmpty(actionTitle) && onClickListener != null) {
            snackbar.setAction(actionTitle, onClickListener);
        }

        snackbar.show();
    }

    public static void showMessage(@NonNull View view,
                                   @StringRes int message,
                                   @StringRes int actionTitle,
                                   @Nullable View.OnClickListener onClickListener) {

        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);

        if (onClickListener != null) {
            snackbar.setAction(actionTitle, onClickListener);
        }

        snackbar.show();
    }

}
