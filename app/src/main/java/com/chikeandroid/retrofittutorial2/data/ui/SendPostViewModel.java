package com.chikeandroid.retrofittutorial2.data.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.chikeandroid.retrofittutorial2.data.AppRepository;
import com.chikeandroid.retrofittutorial2.data.remote.TxnStatus;

public class SendPostViewModel extends ViewModel {

    private final AppRepository mRepository;
    private MutableLiveData<TxnStatus> mStatus;

    public SendPostViewModel(AppRepository mRepository) {
        this.mRepository = mRepository;
    }

    public void sendPost(String title, String body) {
        mRepository.sendPost(title, body);
    }

    public LiveData<TxnStatus> getTxnStatus() {
        mStatus = mRepository.getTxnStatus();
        return mStatus;
    }
}
