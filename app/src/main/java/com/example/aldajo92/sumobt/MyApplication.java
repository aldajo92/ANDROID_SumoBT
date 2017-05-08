package com.example.aldajo92.sumobt;

import android.app.Application;

import com.example.aldajo92.sumobt.dagger.AppComponent;
import com.example.aldajo92.sumobt.dagger.DaggerAppComponent;
import com.example.aldajo92.sumobt.dagger.modules.AppModule;
import com.example.aldajo92.sumobt.dagger.modules.BluetoothModule;

/**
 * Created by aldajo92 on 4/15/17.
 */

public class MyApplication extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .bluetoothModule(new BluetoothModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
