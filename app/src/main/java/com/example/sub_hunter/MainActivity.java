package com.example.sub_hunter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.MotionEvent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.util.Log;
import android.widget.ImageView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView gameView;
    int numberHorizontalPixels;
    int numberVerticalPixels;
    int blockSize;
    int gridWidth = 40;
    int gridHeight;
    float horizontalTouched = -100;
    float verticalTouched = -100;
    int subHorizontalPosition;
    int subVerticalPosition;
    boolean hit = false;
    int shotsTaken;
    int distanceFromSub;
    boolean debugging = true;
    Bitmap blankBitmap;
    Canvas canvas;
    Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Debugging","in onCreath");

        Display display =getWindowManager().getDefaultDisplay();
        Point size =new Point();
        display.getSize(size);
        Log.d("Debugging","screeen size: "+size.toString());
        numberHorizontalPixels = size.x;
        numberVerticalPixels = size.y;
        blockSize = numberHorizontalPixels / gridWidth;
        gridHeight = numberVerticalPixels / blockSize;

        blankBitmap = Bitmap.createBitmap(numberHorizontalPixels,
                numberVerticalPixels,
                Bitmap.Config.ARGB_8888);

        canvas = new Canvas(blankBitmap);
        this.gameView = new ImageView(this);
        paint = new Paint();
        gameView.setImageBitmap(blankBitmap);

        setContentView(gameView);
        draw();

    }
    void draw() {
        gameView.setImageBitmap(blankBitmap);
        // Wipe the screen with a white color
        canvas.drawColor(Color.argb(255, 255, 255, 255));
        paint.setColor(Color.argb(255, 0, 0, 0));

        canvas.drawLine(blockSize * 1, 0,
                blockSize * 1, numberVerticalPixels -1,
                paint);

        // Draw the horizontal lines of the grid
        canvas.drawLine(0, blockSize * 1,
                numberHorizontalPixels -1, blockSize * 1,
                paint);

        paint.setTextSize(blockSize * 2);
        paint.setColor(Color.argb(255, 0, 0, 255));
        canvas.drawText(
                "Shots Taken: " + shotsTaken +
                        " Distance: " + distanceFromSub,
                blockSize, blockSize * 1.75f,
                paint);


        for(int i=0;i<numberHorizontalPixels;i++){

            canvas.drawLine(i+0 ,i+0,i+1,i+1,paint);
        }
    }

    void printDebuggingText() {


    }

}
