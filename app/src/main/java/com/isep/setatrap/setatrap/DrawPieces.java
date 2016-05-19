package com.isep.setatrap.setatrap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Armel on 19/05/2016.
 */
public class DrawPieces extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder = null;
    private Bitmap bitmap;
    private float x,y;
    private Paint paint;

    public DrawPieces(Context context){
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mario);
        paint = new Paint();
        paint.setColor(Color.parseColor("#12345678"));

        x = 50.0f;
        y = 50.0f;
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
        DrawThread drawThread = new DrawThread();
        drawThread.start();
    }

    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(bitmap, x, y, paint);
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
                        if (canvas != null) {
                            onDraw(canvas);
                        }
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