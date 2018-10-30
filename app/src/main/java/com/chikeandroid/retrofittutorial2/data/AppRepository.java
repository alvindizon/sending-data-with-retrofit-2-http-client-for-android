package com.chikeandroid.retrofittutorial2.data;
import android.util.Log;


/**
 * Manages various data sources in this app (network, db, shared prefs)
 */
public class AppRepository {

    private static final String TAG = AppRepository.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static AppRepository sInstance;


    public AppRepository() {
    }

    public synchronized static AppRepository getInstance () {
        Log.d(TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new AppRepository();
                Log.d(TAG, "Made new repository");
            }
        }
        return sInstance;
    }


}
