package com.chikeandroid.retrofittutorial2.data.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.chikeandroid.retrofittutorial2.data.model.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
    private MutableLiveData<Post> mPostResponse;

    public NetworkDataSource(Context mContext) {
        this.mContext = mContext;
        mService = getAPIService();
        mPostResponse = new MutableLiveData<>();
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

    public void sendPost(String title, String body) {
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
                    }

                    @Override
                    public void onNext(Post post) {
                        Log.i(TAG, "post submitted to API." + post.toString());

                        mPostResponse.setValue(post);
                    }
                });
    }


    public MutableLiveData<Post> getPostResponse() {
        return this.mPostResponse;
    }
}
