package com.chikeandroid.retrofittutorial2.data.remote;

import android.content.Context;
import android.util.Log;

import static com.chikeandroid.retrofittutorial2.data.remote.ApiUtils.getAPIService;

public class NetworkDataSource {

    public static final String TAG = NetworkDataSource.class.getSimpleName();

    private APIService mService;

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static NetworkDataSource sInstance;
    private final Context mContext;

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
}
