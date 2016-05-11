package com.example.zachfroeber.testgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Zach Froeber on 4/4/2016.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public static final int WIDTH = 1440;
    public static final int HEIGHT = 2560;
    public static final int MOVESPEED = -5;
    private MainThread thread;
    private Background bg;
    private Player player;


    public GamePanel(Context contect){
        super(contect);

        //add callback to surface holder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        //make gamepanel focusable
        setFocusable(true);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        bg = new Background(BitmapFactory.decodeResource(getResources(),R.drawable.background));
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.characters), 200, 200, 4);

        thread.setBrunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry){
            try {
                thread.setBrunning(false);
                thread.join();

            } catch (InterruptedException e) {e.printStackTrace();}
            retry = false;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        if (event.getAction()==MotionEvent.ACTION_DOWN) {
            player.setMovement(-1);
        }
        return super.onTouchEvent(event);

    }


    public void update() {
        bg.update();
        player.update();
        if (200 + player.getIx() > 1311 || player.getIx() < 129){
            thread.setBrunning(false);
        }

    }



    @Override
    public void draw(Canvas canvas) {
        final float scaleFactorX = getWidth() / (WIDTH*1.f);
        final float scaleFactorY = getHeight() / (HEIGHT*1.f);
        System.out.println(scaleFactorX + scaleFactorY);
        if (canvas != null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            player.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }
}
