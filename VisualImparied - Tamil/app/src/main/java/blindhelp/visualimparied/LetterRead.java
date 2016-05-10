package blindhelp.visualimparied;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.provider.MediaStore;
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


public class LetterRead extends ContactSuperClass implements View.OnClickListener {

    GestureDetector gestureDetector;
    private Button b1,b2,b3,b4,b5,b6;
    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    private long pressTime;
    private long lastPressTime;
    MediaPlayer mediaPlayer;
    Vibrator v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_read);
        getSupportActionBar().hide();// this is the code for the hide applicatoin name
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mediaPlayer=new MediaPlayer();
        mediaPlayer=MediaPlayer.create(this,R.raw.pad_instruction1);
        mediaPlayer.start();
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);
        gestureDetector=new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if(velocityX <-200.0f){
                    Toast.makeText(getApplicationContext(), "swap happens", Toast.LENGTH_SHORT).show();
                    LetterFind();
                }
                else if(-velocityX <- 200.0f){
                    Toast.makeText(getApplicationContext()," Right swap happens", Toast.LENGTH_SHORT).show();
                    // deleteNumber();
                }
                else if (-velocityY <- 50.0f){
                    Toast.makeText(getApplicationContext()," down swap happens", Toast.LENGTH_SHORT).show();
                    backToMenu();
                }
                else if (velocityY <- 50.0f){
                    Toast.makeText(getApplicationContext()," up swap happens", Toast.LENGTH_SHORT).show();
                    backToSave();
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
        //set the buttons parameters
        b1 = (Button) findViewById(R.id.button8);
        b2 = (Button) findViewById(R.id.button10);
        b3 = (Button) findViewById(R.id.button12);
        b4 = (Button) findViewById(R.id.button9);
        b5 = (Button) findViewById(R.id.button11);
        b6 = (Button) findViewById(R.id.button13);


        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        onBackPressed();
    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
    private void backToMenu() {
        Intent intent=new Intent(this,Sbhadatha.class);
        startActivity(intent);
        this.finish();
    }

    private void backToSave() {
        Intent intent=new Intent(this,EditName.class);
        startActivity(intent);
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
                case R.id.button8:
                    number1 = 1;
                    Toast.makeText(getApplicationContext(), "Double Click Event number 1 clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button10:
                    number2 = 1;
                    Toast.makeText(getApplicationContext(), "Double Click Event number 2 clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button12:
                    number3 = 1;
                    Toast.makeText(getApplicationContext(), "Double Click Event number 3 clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button9:
                    number4 = 1;
                    Toast.makeText(getApplicationContext(), "Double Click Event number 4 clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button11:
                    number5 = 1;
                    Toast.makeText(getApplicationContext(), "Double Click Event number 5 clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button13:
                    number6 = 1;
                    Toast.makeText(getApplicationContext(), "Double Click Event number 6 clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
            vibrate();
        }else {
            mediaPlayer.release();
            switch (v.getId()) {
                case R.id.button8:
                    mediaPlayer = MediaPlayer.create(this, R.raw.one);
                    mediaPlayer.start();
                    break;
                case R.id.button10:
                    mediaPlayer = MediaPlayer.create(this, R.raw.two);
                    mediaPlayer.start();
                    break;
                case R.id.button12:
                    mediaPlayer = MediaPlayer.create(this, R.raw.three);
                    mediaPlayer.start();
                    break;
                case R.id.button9:
                    mediaPlayer = MediaPlayer.create(this, R.raw.four);
                    mediaPlayer.start();
                    break;
                case R.id.button11:
                    mediaPlayer = MediaPlayer.create(this, R.raw.five);
                    mediaPlayer.start();
                    break;
                case R.id.button13:
                    mediaPlayer = MediaPlayer.create(this, R.raw.six);
                    mediaPlayer.start();
                    break;
            }
            lastPressTime = pressTime;
        }
    }

    private void destroy() {
        v.cancel();
    }

    private void vibrate() {
        v.vibrate(400);
    }

    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
