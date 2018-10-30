package com.chikeandroid.retrofittutorial2.data;

import android.content.Context;
import android.util.Log;

import com.chikeandroid.retrofittutorial2.data.remote.NetworkDataSource;

public class InjectorUtils {

    private static final String TAG = InjectorUtils.class.getSimpleName();

    public static AppRepository provideRepository(Context context) {
        NetworkDataSource networkDataSource = provideNetworkDataSource(context);
        Log.d(TAG, "Creating repository...");
        return AppRepository.getInstance( networkDataSource);
    }


    public static NetworkDataSource provideNetworkDataSource(Context context) {
        Log.d(TAG, "Creating NetworkDataSource...");
        return new NetworkDataSource(context.getApplicationContext());
    }


}
