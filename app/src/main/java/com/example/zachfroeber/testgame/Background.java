package com.example.zachfroeber.testgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Zach Froeber on 4/4/2016.
 */
public class Background {
    private Bitmap image;
    private int x, y, dx;

    public Background(Bitmap res) {
        image = res;
        dx = GamePanel.MOVESPEED;

    }

    public void update() {

        y -= dx;
        if (y > (GamePanel.HEIGHT)) {
            y = 0;
        }

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
        canvas.drawBitmap(image, x, y - GamePanel.HEIGHT, null);


    }

    public Bitmap getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
