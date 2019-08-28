package com.example.sub_hunter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    int subHorizontalPosition=20;
    int subVerticalPosition=20;
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
        this.blockSize = numberHorizontalPixels / gridWidth;
        gridHeight = numberVerticalPixels / this.blockSize;

        blankBitmap = Bitmap.createBitmap(numberHorizontalPixels,
                numberVerticalPixels,
                Bitmap.Config.ARGB_8888);

        canvas = new Canvas(blankBitmap);
        this.gameView = new ImageView(this);
        paint = new Paint();
        this.gameView.setImageBitmap(blankBitmap);

        setContentView(gameView);
        draw();

    }
    void draw() {
        gameView.setImageBitmap(blankBitmap);
        // Wipe the screen with a white color
        canvas.drawColor(Color.argb(255, 255, 255, 255));
        paint.setColor(Color.argb(255, 0, 0, 0));
        for(int i=0;i<numberHorizontalPixels;i++){
            canvas.drawLine(this.blockSize * i, 0,
                    blockSize * i, numberVerticalPixels -1,
                    paint);
        }

        for(int i=0;i<numberHorizontalPixels;i++){
            canvas.drawLine(0, this.blockSize * i,
                    numberHorizontalPixels -1, blockSize * i,
                    paint);
        }
        // Draw the player's shot
        canvas.drawRect(horizontalTouched * blockSize,
                verticalTouched * blockSize,
                (horizontalTouched * blockSize) + blockSize,
                (verticalTouched * blockSize)+ blockSize,
                paint );



        paint.setTextSize(blockSize * 2);
        paint.setColor(Color.argb(255, 0, 0, 255));
        canvas.drawText(
                "Shots Taken: " + shotsTaken +
                        " Distance: " + distanceFromSub,
                blockSize, blockSize * 1.75f,
                paint);

       // printDebuggingText();
    }

    public void printDebuggingText(){
        paint.setTextSize(blockSize);
        canvas.drawText("numberHorizontalPixels = "
                        + numberHorizontalPixels,
                50, blockSize * 3, paint);
        canvas.drawText("numberVerticalPixels = "
                        + numberVerticalPixels,
                50, blockSize * 4, paint);
        canvas.drawText("blockSize = " + blockSize,
                50, blockSize * 5, paint);
        canvas.drawText("gridWidth = " + gridWidth,
                50, blockSize * 6, paint);
        canvas.drawText("gridHeight = " + gridHeight,
                50, blockSize * 7, paint);
        canvas.drawText("horizontalTouched = " +
                        horizontalTouched, 50,
                blockSize * 8, paint);
        canvas.drawText("subHorizontalPosition = " +
                        subHorizontalPosition, 50,
                blockSize * 10, paint);
        canvas.drawText("subVerticalPosition = " +
                        subVerticalPosition, 50,
                blockSize * 11, paint);
        canvas.drawText("hit = " + hit,
                50, blockSize * 12, paint);
        canvas.drawText("shotsTaken = " +
                        shotsTaken,
                50, blockSize * 13, paint);
        canvas.drawText("debugging = " + debugging,
                50, blockSize * 12, paint);


    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.d("Debugging", "In onTouchEvent");
        // Has the player removed their finger from the screen?
        if((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {

            takeShot(motionEvent.getX(),motionEvent.getY());

            Log.d("Debugging", "Lifted the finger");


            // Process the player's shot by passing the
            // coordinates of the player's finger to takeShot
            //takeShot(motionEvent.getX(), motionEvent.getY());
        }

        return true;
    }
    protected void takeShot(float x,float y){

        shotsTaken ++;
        String shot_x=Float.toString(x);
        String shot_y=Float.toString(y);
        Log.d("Debugging","shot: "+shot_x+" "+shot_y);
         horizontalTouched = (int)x/ blockSize;
         verticalTouched = (int)y/ blockSize;

        setContentView(gameView);
        canvas.drawCircle((horizontalTouched-1)*blockSize,(verticalTouched-1)*blockSize,5,paint);


        hit = horizontalTouched == subHorizontalPosition && verticalTouched == subVerticalPosition;

        int horizontalGap = (int)horizontalTouched -
                subHorizontalPosition;
        int verticalGap = (int)verticalTouched -
                subVerticalPosition;
            // Use Pythagoras's theorem to get the
            // distance travelled in a straight line
        distanceFromSub = (int)Math.sqrt(
                ((horizontalGap * horizontalGap) +
                        (verticalGap * verticalGap)));
// If there is a hit call boom
        if(hit)
            boom();
// Otherwise call draw as usual
        else draw();
    }
    protected void boom(){
        gameView.setImageBitmap(blankBitmap);
        // Wipe the screen with a red color
        canvas.drawColor(Color.argb(255, 255, 0, 0));
        // Draw some huge white text
        paint.setColor(Color.argb(255, 255, 255, 255));
        paint.setTextSize(blockSize * 10);
        canvas.drawText("BOOM!", blockSize * 4,
                blockSize * 14, paint);
        // Draw some text to prompt restarting
        paint.setTextSize(blockSize * 2);
        canvas.drawText("Take a shot to start again",
                blockSize * 8,
                blockSize * 18, paint);
        // Start a new game
        newGame();
    }
    void newGame(){
        shotsTaken=-1;
        //draw();
    }

}
