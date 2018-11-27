package com.example.gesturezoom;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {


    GestureDetector detector;
    ImageView imageView;

    Bitmap bitmap;

    int width, height;

    float currentScale = 1.0f;
    Matrix matrix;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detector = new GestureDetector(this, this);
        imageView = (ImageView) findViewById(R.id.show);
        matrix = new Matrix();

        bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.flower);

        width = bitmap.getWidth();
        height = bitmap.getHeight();

        imageView.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.flower));

    }

    @Override
    public boolean onTouchEvent(MotionEvent me) {
        return detector.onTouchEvent(me);

    }

    @Override
    public boolean onDown(MotionEvent e) {

        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        velocityX = velocityX > 4000 ? 4000 : velocityX;
        velocityX = velocityX < -4000 ? -4000 : velocityX;

        currentScale += currentScale *velocityX / 4000.0f;
        currentScale = currentScale > 0.01 ? currentScale : 0.01f;
       // Toast.makeText(this, "Scale:" )
        matrix.reset();

        matrix.setScale(currentScale, currentScale, width/2, height/2);
        BitmapDrawable tmp = (BitmapDrawable) imageView.getDrawable();

        if (!tmp.getBitmap().isRecycled()) {
            tmp.getBitmap().recycle();

        }
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);


        imageView.setImageBitmap(bitmap2);
        return true;
    }
}
