package blindhelp.visualimparied;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


public class NumberRead extends NumberPad implements View.OnClickListener {

    private GestureDetector gestureDetector;
    private Vibrator v;
    private Button b1,b2,b4,b5;
    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    private long lastPressTime;
    long pressTime;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_read);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);
        ButtonPress();
        mediaPlayer=MediaPlayer.create(this,R.raw.pad_instruction1);
        mediaPlayer.start();
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (velocityX < -200.0f) {
                     PhoneNumberSearch();
                    //ReadNumber();
                } else if (-velocityX < -200.0f) {
                    vibtare();
                    //deleteNumber();
                } else if (-velocityY < -50.0f) {
                    backToMenu();
                }
                else if (velocityY < -50.0f) {
                    BackToMenu();
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
        onBackPressed();
    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
    private void backToMenu() {
        Intent intent=new Intent(this,EditName.class);
        startActivity(intent);
        this.finish();
    }
    public void BackToMenu(){
        Intent intent=new Intent(this,EditName.class);
        startActivity(intent);
    }

    private void ButtonPress() {

        //set the buttons parameters
        b1 = (Button) findViewById(R.id.button5);
        b2 = (Button) findViewById(R.id.button7);

        b4 = (Button) findViewById(R.id.button6);
        b5 = (Button) findViewById(R.id.button8);


        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Get current time in nano seconds.
        pressTime = System.currentTimeMillis();

        // If double click...
        if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {
            mediaPlayer.release();
            destroy();
            switch (v.getId()) {
                case R.id.button5:
                    number1 = 1;
                    break;
                case R.id.button7:
                    number2 = 1;
                    break;
                case R.id.button6:
                    number3 = 1;
                    break;
                case R.id.button8:
                    number4 = 1;
                    break;

            }vibtare();
        }else
        {
            mediaPlayer.release();
            switch (v.getId()) {
                case R.id.button5:
                    mediaPlayer=MediaPlayer.create(this,R.raw.one);
                    mediaPlayer.start();
                    break;
                case R.id.button7:
                    mediaPlayer=MediaPlayer.create(this,R.raw.two);
                    mediaPlayer.start();
                    break;
                case R.id.button6:
                    mediaPlayer=MediaPlayer.create(this,R.raw.three);
                    mediaPlayer.start();
                    break;
                case R.id.button8:
                    mediaPlayer=MediaPlayer.create(this,R.raw.four);
                    mediaPlayer.start();
                    break;

            }
            lastPressTime = pressTime;
        }

    }

    private void vibtare() {
        v.vibrate(400);
    }

    private void destroy() {
        v.cancel();
    }

    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
