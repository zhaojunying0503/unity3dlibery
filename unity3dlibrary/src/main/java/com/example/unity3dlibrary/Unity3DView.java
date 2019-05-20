package com.example.unity3dlibrary;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.unity3d.player.UnityPlayer;


public class Unity3DView extends com.unity3d.player.UnityPlayer
{
    private String TAG ="Unity3DView";
    private int mode=0;
    private float oldX;
    private float oldY;
    private ScaleGestureDetector scaleGestureDetector;

    public Unity3DView(ContextWrapper contextWrapper) {
        super(contextWrapper);
    }

    /**
     * 后面是在sdcard
     */
    public void setModlePath(String path){
        Log.i(TAG,"file://"+ path);
        UnityPlayer.UnitySendMessage("Camera","loadModeFromAndroid","file://"+ path);
    }

    public void setSdcardUnderModlePath(String path){
        Log.i(TAG,"file://"+ Environment.getExternalStorageDirectory()+"/"+path);
        UnityPlayer.UnitySendMessage("Camera","loadModeFromAndroid","file://"+ Environment.getExternalStorageDirectory()+"/"+path);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                oldX = event.getX();
                oldY = event.getY();
                mode = 1;
                return true;
            case MotionEvent.ACTION_UP:
                mode = 0;
                return true;
            case MotionEvent.ACTION_POINTER_UP:
                mode -= 1;
                return scaleGestureDetector.onTouchEvent(event);
            case MotionEvent.ACTION_POINTER_DOWN:
                mode += 1;
                return scaleGestureDetector.onTouchEvent(event);
            case MotionEvent.ACTION_MOVE:
                if (mode >= 2) {
                    return scaleGestureDetector.onTouchEvent(event);
                }else{
                    float dx = (float) ((event.getX() -oldX)*0.003);
                    float dy = -(float) ((event.getY() - oldY)*0.003);
                    UnityPlayer.UnitySendMessage("Camera","disXY",dx+"_"+dy);
                    Log.i("zhao","调用：dx="+dx+"   dy="+dy);
                    return false;
                }
            default:
                return true;
        }
    }


    public  void initScaleGestureDetector(Context cotext) {
        scaleGestureDetector = new ScaleGestureDetector(cotext, new ScaleGestureDetector.OnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                Log.i(TAG, "focusX = " + detector.getFocusX());       // 缩放中心，x坐标
                Log.i(TAG, "focusY = " + detector.getFocusY());       // 缩放中心y坐标
                Log.i(TAG, "scale = " + detector.getScaleFactor());   // 缩放因子
                if(detector.getScaleFactor()<1){
                    UnityPlayer.UnitySendMessage("Camera","ZoomMax","");
                }else{
                    UnityPlayer.UnitySendMessage("Camera","ZoomMin","");
                }
                return  true;
            }
            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                return  true;
            }
            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {
            }
        });

    }
}
