package com.chikeandroid.retrofittutorial2.data.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.chikeandroid.retrofittutorial2.data.model.Post;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.chikeandroid.retrofittutorial2.data.remote.ApiUtils.getAPIService;

public class NetworkDataSource {

    public static final String TAG = NetworkDataSource.class.getSimpleName();

    private APIService mService;

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static NetworkDataSource sInstance;
    private final Context mContext;
    private MutableLiveData<TxnStatus> mStatus;

    public NetworkDataSource(Context mContext) {
        this.mContext = mContext;
        mService = getAPIService();
    }

    public static NetworkDataSource getInstance(Context context) {
        Log.d(TAG, "Getting the network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new NetworkDataSource(context.getApplicationContext());
                Log.d(TAG, "Made new network data source");
            }
        }
        return sInstance;
    }

    public LiveData<TxnStatus> sendPost(String title, String body) {
        mService.savePost(title, body, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Post>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Unable to post submit to API.");
                        mStatus.setValue(TxnStatus.TXN_FAILED);
                    }

                    @Override
                    public void onNext(Post post) {
                        Log.i(TAG, "post submitted to API." + post.toString());
                        mStatus.setValue(TxnStatus.TXN_SUCCESS);
                    }
                });

        return mStatus;
    }
}
