package com.chikeandroid.retrofittutorial2.data;

import android.content.Context;
import android.util.Log;

import com.chikeandroid.retrofittutorial2.data.remote.NetworkDataSource;
import com.chikeandroid.retrofittutorial2.data.ui.SendPostViewModelFactory;

public class InjectorUtils {

    private static final String TAG = InjectorUtils.class.getSimpleName();

    public static AppRepository provideRepository(Context context) {
        NetworkDataSource networkDataSource = provideNetworkDataSource(context);
        Log.d(TAG, "Creating repository...");
        return AppRepository.getInstance( networkDataSource);
    }


    public static NetworkDataSource provideNetworkDataSource(Context context) {
        Log.d(TAG, "Creating NetworkDataSource...");
        return NetworkDataSource.getInstance(context.getApplicationContext());
    }

    public static SendPostViewModelFactory provideSendPostViewModelFactory(Context context) {
        AppRepository repository = provideRepository(context.getApplicationContext());
        Log.d(TAG, "Creating SendPostViewModelFactory...");
        return new SendPostViewModelFactory(repository);
    }


}
