package com.example.zachfroeber.testgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Zach Froeber on 4/4/2016.
 */
public class Player extends GameObject {

    private Bitmap spritesheet;
    private int Iscore;
    private double Ddya;
    private boolean Bup;
    private boolean Bplaying;
    private Animation animation = new Animation();
    private long LstartTime;
    private int movement;


    public Player (Bitmap res, int w, int h, int numFrames){
        this.Ix=500;
        this.Iy = 2300;
        Iscore = 0;
        Iheight = h;
        Iwidth = w;
        movement = 5;

        Bitmap [] image = new Bitmap [numFrames];
        spritesheet = res;

        for (int i = 0; i < numFrames; i++){
            image [i] = Bitmap.createBitmap(spritesheet, i*Iwidth, 0, Iwidth, Iheight);
        }

        animation.setFrames(image);
        animation.setLdelay(10);
        LstartTime = System.nanoTime();

    }


    public void setUp (boolean b) {Bup = b;}

    public void update(){
        long Lelapsed = (System.nanoTime()-LstartTime)/1000000;
        if (Lelapsed > 100){
            Iscore++;
            LstartTime = System.nanoTime();

        }
        animation.update();
        Ix += movement;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(animation.getImage(), Ix, Iy, null);

    }
    public int getScore() {return Iscore;}
    public boolean getPlaying() {return Bplaying;}
    public void setPlaying (boolean a) {Bplaying = a;}
    public void resetDdya () {Ddya = 0;}
    public void resetScore () {Iscore = 0;}
    public Bitmap getSpritesheet() {
        return spritesheet;
    }

    public int getIscore() {
        return Iscore;
    }

    public double getDdya() {
        return Ddya;
    }

    public boolean isBup() {
        return Bup;
    }

    public boolean isBplaying() {
        return Bplaying;
    }

    public Animation getAnimation() {
        return animation;
    }

    public long getLstartTime() {
        return LstartTime;
    }

    public void setSpritesheet(Bitmap spritesheet) {
        this.spritesheet = spritesheet;
    }

    public void setIscore(int iscore) {
        Iscore = iscore;
    }

    public void setDdya(double ddya) {
        Ddya = ddya;
    }

    public void setBup(boolean bup) {
        Bup = bup;
    }

    public void setBplaying(boolean bplaying) {
        Bplaying = bplaying;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public void setLstartTime(long lstartTime) {
        LstartTime = lstartTime;
    }

    public void setMovement (int m) {movement = movement*m;}

}

