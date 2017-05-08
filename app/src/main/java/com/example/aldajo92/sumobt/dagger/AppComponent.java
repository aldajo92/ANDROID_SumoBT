package com.example.aldajo92.sumobt.dagger;

import com.example.aldajo92.sumobt.activities.BaseActivity;
import com.example.aldajo92.sumobt.activities.MainActivity;
import com.example.aldajo92.sumobt.dagger.modules.AppModule;
import com.example.aldajo92.sumobt.dagger.modules.BluetoothModule;
import com.example.aldajo92.sumobt.dagger.modules.PresenterModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by aldajo92 on 4/14/17.
 */

@Singleton
@Component(modules = {PresenterModule.class, AppModule.class, BluetoothModule.class})
public interface AppComponent {

    void inject(BaseActivity activity);

    void inject(MainActivity activity);

}
