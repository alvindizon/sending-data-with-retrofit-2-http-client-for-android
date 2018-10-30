package com.chikeandroid.retrofittutorial2.data;
import android.util.Log;

import com.chikeandroid.retrofittutorial2.data.remote.NetworkDataSource;


/**
 * Manages various data sources in this app (network, db, shared prefs)
 */
public class AppRepository {

    private static final String TAG = AppRepository.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private final NetworkDataSource mNetworkDataSource;
    private static AppRepository sInstance;


    public AppRepository(NetworkDataSource networkDataSource) {
        this.mNetworkDataSource = networkDataSource;
    }

    public synchronized static AppRepository getInstance (NetworkDataSource networkDataSource) {
        Log.d(TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new AppRepository(networkDataSource);
                Log.d(TAG, "Made new repository");
            }
        }
        return sInstance;
    }


}
