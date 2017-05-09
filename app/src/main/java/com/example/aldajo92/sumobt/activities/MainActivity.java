package com.example.aldajo92.sumobt.activities;

import android.os.Bundle;

import com.example.aldajo92.sumobt.R;
import com.jmedeisis.bugstick.Joystick;
import com.jmedeisis.bugstick.JoystickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.joystick)
    Joystick joystick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        joystick.setJoystickListener(new JoystickListener() {
            @Override
            public void onDown() {
                showlog("down");
            }

            @Override
            public void onDrag(float degrees, float offset) {
                showlog("drag degree=" + degrees + " offset=" + offset);
                showlog("drag v=" +
                        (int) (offset * 400 * Math.sin(Math.toRadians(degrees))) +
                        " w=" +
                        (int) (offset * 400 * Math.cos(Math.toRadians(degrees)))
                );
                if (getBluetoothService().getIsBTConnected()) {
                    sendMessage("m-" + (int) offset * 400 + "-" );
                }
            }

            @Override
            public void onUp() {
                sendMessage("f-0-0");
            }
        });
    }


}
