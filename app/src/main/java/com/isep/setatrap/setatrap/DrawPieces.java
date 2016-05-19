package com.isep.setatrap.setatrap;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Armel on 19/05/2016.
 */
public class DrawPieces extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder = null;

    public DrawPieces(Context context){
        super(context);
        init();
    }
    public DrawPieces(Context context,AttributeSet attrs){
        super(context,attrs);
        init();
    }
    public DrawPieces(Context context,AttributeSet attrs, int defStyle){
        super(context,attrs, defStyle);
        init();
    }

    public void init(){
        mHolder = getHolder();
        mHolder.addCallback(this);
    }

    protected void onDraw(Canvas canvas){

    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private class DrawThread extends Thread {
        boolean keepDrawing = true;

        public void run(){
            while (keepDrawing){
                Canvas canvas = null;
                try{
                    canvas = mHolder.lockCanvas();
                    synchronized(mHolder){
                        onDraw(canvas);
                    }

                }
                finally {
                    if (canvas != null) {
                        mHolder.unlockCanvasAndPost(canvas);
                    }
                }
                try {
                    Thread.sleep(20);
                }catch(InterruptedException e){

                }
            }


        }



    }
}