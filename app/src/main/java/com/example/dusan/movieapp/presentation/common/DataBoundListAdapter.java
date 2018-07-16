package com.example.dusan.movieapp.presentation.common;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.view.ViewGroup;

public abstract class DataBoundListAdapter<T, V extends ViewDataBinding> extends ListAdapter<T, DataBoundViewHolder<V>> {

    private V mBinding;

    protected DataBoundListAdapter(@NonNull AsyncDifferConfig.Builder<T> diffCallback) {
        super(diffCallback.build());
    }

    @NonNull
    @Override
    public DataBoundViewHolder<V> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBinding = createBinding(parent);
        return new DataBoundViewHolder<>(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DataBoundViewHolder<V> holder, int position) {
        bind(holder.getBinding(), getItem(position));
        holder.getBinding().executePendingBindings();
    }

    protected abstract V createBinding(ViewGroup parent);

    protected abstract void bind(V binding, T item);
}
