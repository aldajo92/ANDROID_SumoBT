package com.example.aldajo92.sumobt.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast

import com.example.aldajo92.sumobt.BluetoothService
import com.example.aldajo92.sumobt.R

/**
 * Created by aldajo92 on 4/14/17.
 */

open class BaseActivity : AppCompatActivity(), BluetoothService.ConnectedListener {

    val bluetoothService by lazy {
        BluetoothService(this)
    }

    private val dialog by lazy {
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.loader_loading_title))
                .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bluetoothService.checkBluetoothState(this)
        bluetoothService.setConnectedListener(this)
    }

    override fun onResume() {
        super.onResume()
        if (!bluetoothService.isBTConnected) {

        }
        showMessage("is connected? " + bluetoothService.isBTConnected!!)
    }

    fun showWaitingOverlay() {
        dialog.show()
    }

    fun hideWaitingOverlay() {
        if (null != dialog) dialog.hide()
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showlog(message: String) {
        Log.i(TAG, "showlog: $message")
    }

    fun sendMessage(data: String) {
        bluetoothService.bt.send(data + "\n", false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        bluetoothService.onActivityResult(requestCode, resultCode, data, this)
    }

    override fun onDeviceConnected() {
        hideWaitingOverlay()
    }

    override fun onDeviceDisconnected() {
        showWaitingOverlay()
    }

    override fun onDeviceConnectionFailed() {

    }

    companion object {
        private val TAG = BaseActivity::class.java.name
    }
}
