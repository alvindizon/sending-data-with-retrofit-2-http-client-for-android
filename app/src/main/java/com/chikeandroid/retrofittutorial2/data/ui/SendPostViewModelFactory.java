package com.chikeandroid.retrofittutorial2.data.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.chikeandroid.retrofittutorial2.data.AppRepository;

public class SendPostViewModelFactory  extends ViewModelProvider.NewInstanceFactory {

    private final AppRepository mRepository;

    public SendPostViewModelFactory(AppRepository mRepository) {
        this.mRepository = mRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SendPostViewModel(mRepository);
    }
}
