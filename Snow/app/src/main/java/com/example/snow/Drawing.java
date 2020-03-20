package com.example.snow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

class Snowflake{
    float x, y, velocity;
    float r = 20;


    public Snowflake() {
        x = (float) Math.random();
        y = (float) Math.random();

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getVelocity() {
        return velocity;
    }

    public float getR() {
        return r;
    }

    void fall(){
        y += velocity;

    }
}

public class Drawing extends View {

    Snowflake[] snowflakes;
    Paint paint = new Paint();
    float w;
    float h;

    public Drawing(Context context) {
        super(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        w = canvas.getWidth();
        h = canvas.getHeight();
        canvas.drawColor(Color.BLUE);
        float x, y, r;
        for (int i = 0; i < snowflakes.length; i++) {
            x = (snowflakes[i].getX()) * w;
            r = snowflakes[i].getR();
            if (snowflakes[i].getY() < 1) {
                y = (snowflakes[i].getY()) * h;
            }else{

                y = (snowflakes[i].getY()-1) * h;
            }
            paint.setColor(Color.parseColor("#FF0000"));
            canvas.drawCircle(x, y, r, paint);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for (int i = 0; i < 20; i++) {
            snowflakes[i].fall();
        }
        invalidate();
        return true;
    }

    public Drawing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        snowflakes = new Snowflake[20];
        for (int i = 0; i < 20; i++) {
            snowflakes[i] = new Snowflake();
        }
    }
}


