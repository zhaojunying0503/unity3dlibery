package com.example.lang.unity3dlibery;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.unity3dlibrary.Unity3DView;

public class MainActivity extends AppCompatActivity{

    Unity3DView mUnity3DView;
    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUnity3DView = new Unity3DView(this);
        mUnity3DView.initScaleGestureDetector(this);
        setContentView(mUnity3DView);
        mUnity3DView.setSdcardUnderModlePath("ceshi.unity3d");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }


    }



    // Quit Unity
    @Override
    protected void onDestroy ()
    {
        mUnity3DView.quit();
        super.onDestroy();
    }

    // Pause Unity
    @Override
    protected void onPause()
    {
        super.onPause();
        mUnity3DView.pause();
    }

    // Resume Unity
    @Override
    protected void onResume()
    {
        super.onResume();
        mUnity3DView.resume();
    }

    // Low Memory Unity
    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        mUnity3DView.lowMemory();
    }

    // Trim Memory Unity
    @Override
    public void onTrimMemory(int level)
    {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_RUNNING_CRITICAL)
        {
            mUnity3DView.lowMemory();
        }
    }

    // This ensures the layout will be correct.
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mUnity3DView.configurationChanged(newConfig);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        mUnity3DView.windowFocusChanged(hasFocus);
    }


}
