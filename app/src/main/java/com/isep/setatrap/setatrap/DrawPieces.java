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
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Armel on 19/05/2016.
 */
public class DrawPieces extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder = null;
    private Bitmap bitmap;
    private Bitmap resizedBitmap;
    private Bitmap bitmapOrange;
    private Bitmap resizedBitmapOrange;
    private Bitmap resizedBitmapGris;
    private Bitmap bitmapGris;
    private Bitmap bitmapBouton;
    private float x,y;
    private Paint paint;

    public DrawPieces(Context context){
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mario);
        resizedBitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, false);

        bitmapOrange = BitmapFactory.decodeResource(getResources(), R.drawable.carre_orange);
        resizedBitmapOrange = Bitmap.createScaledBitmap(bitmapOrange, 200, 200, false);

        bitmapGris = BitmapFactory.decodeResource(getResources(), R.drawable.carre_gris);
        resizedBitmapGris = Bitmap.createScaledBitmap(bitmapGris, 200, 200, false);

        Button bouton = (Button)findViewById(R.id.boutonMario);
        bitmapBouton = BitmapFactory.decodeResource(getResources(), R.id.boutonMario);


        paint = new Paint();
        paint.setColor(Color.parseColor("#12345678"));

        x = 50.0f;
        y = 200.0f;
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

        for(int i=0; i<5; ++i){
            for(int j=0; j<5; ++j){
                if((i+j)%2 == 0 ){
                    canvas.drawBitmap(resizedBitmapGris, x+j*200, y+i*200, paint);
                }else{
                    canvas.drawBitmap(resizedBitmapOrange, x+j*200, y+i*200, paint);
                }
            }

        }
        canvas.drawBitmap(resizedBitmap, x+50, y+50, paint);




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