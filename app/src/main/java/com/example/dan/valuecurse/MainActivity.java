package com.example.dan.valuecurse;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    private static final String ON = "On";
    private static final String OFF = "Off";

    private Button mOnOffButton;
    private Camera mCamera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOnOffButton = (Button)findViewById(R.id.torch);
        mOnOffButton.setText(ON);
        mOnOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Camera.Parameters params = mCamera.getParameters();

                if (params.getFlashMode().equals(Camera.Parameters.FLASH_MODE_OFF)) {
                    mOnOffButton.setText(OFF);
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    mCamera.setParameters(params);
                } else {
                    mOnOffButton.setText(ON);
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    mCamera.setParameters(params);
                }



            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        mCamera = Camera.open();
    }

    @Override
    protected void onPause() {
        if (mCamera != null){
            mCamera.release();
            mCamera = null;
        }
        super.onPause();
    }

}
