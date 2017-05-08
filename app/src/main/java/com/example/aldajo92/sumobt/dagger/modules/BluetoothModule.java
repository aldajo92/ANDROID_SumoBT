package com.example.aldajo92.sumobt.dagger.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.aldajo92.sumobt.BluetoothService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BluetoothModule {

    Application mApplication;

    public BluetoothModule(Application application) {
        mApplication = application;
    }

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    // Application reference must come from PresenterModule.class
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
        // Application reference must come from PresenterModule.class
    BluetoothService providesBluethootService(Application application) {
        return new BluetoothService(application);
    }

}