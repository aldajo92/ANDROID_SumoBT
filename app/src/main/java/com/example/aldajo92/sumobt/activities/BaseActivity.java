package com.example.aldajo92.sumobt.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.aldajo92.sumobt.BluetoothService;
import com.example.aldajo92.sumobt.MyApplication;
import com.example.aldajo92.sumobt.dagger.AppComponent;

import javax.inject.Inject;

/**
 * Created by aldajo92 on 4/14/17.
 */

public class BaseActivity extends AppCompatActivity implements BluetoothService.ConnectedListener {

    private static String TAG = BaseActivity.class.getName();

    @Inject
    BluetoothService bluetoothService;

    AppComponent activityComponent;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = ((MyApplication) getApplication()).getAppComponent();
        activityComponent.inject(this);

        bluetoothService.checkBluetoothState(this);
        bluetoothService.setConnectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!bluetoothService.getIsBTConnected()){

        }
        showMessage("is connected? " + bluetoothService.getIsBTConnected());
    }

    public AppComponent getActivityComponent() {
        return activityComponent;
    }

    public BluetoothService getBluetoothService() {
        return bluetoothService;
    }

    public void showWaitingOverlay(){
        dialog = ProgressDialog.show(this, "", "Loading. Please wait...", true);
    }

    public void hideWaitingOverlay(){
        if(null != dialog)  dialog.hide();
    }

    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showlog(String message){
        Log.i(TAG, "showlog: " + message);
    }

    public void sendMessage(String data){
        bluetoothService.getBt().send(data + "\n", false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        bluetoothService.onActivityResult(requestCode, resultCode, data, this);
    }

    @Override
    public void onDeviceConnected() {
        hideWaitingOverlay();
    }

    @Override
    public void onDeviceDisconnected() {
        showWaitingOverlay();
    }

    @Override
    public void onDeviceConnectionFailed() {

    }
}
