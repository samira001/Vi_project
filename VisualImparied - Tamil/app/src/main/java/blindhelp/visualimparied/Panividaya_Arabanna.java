package blindhelp.visualimparied;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;


public class Panividaya_Arabanna extends LetterInput implements OnClickListener, TextToSpeech.OnInitListener{

    private MediaPlayer mediaPlayer;
    private GestureDetector gestureDetector;
    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    private long pressTime;
    private long lastPressTime;
    private Vibrator v;
    private Button b1,b2,b3,b4,b5,b6;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panividaya__arabanna);
        getSupportActionBar().hide();// this is the code for the hide applicatoin name
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mediaPlayer=new MediaPlayer();
        mediaPlayer=MediaPlayer.create(this,R.raw.pad_instruction1);
        mediaPlayer.start();
        onBackPressed();
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);
        gestureDetector=new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                mediaPlayer.release();
                if(velocityX <-200.0f){
                    Toast.makeText(getApplicationContext(), "swap happens", Toast.LENGTH_SHORT).show();
                    //CheckInputType();
                    LetterFind();
                }
                else if(-velocityX <- 200.0f){
                    Toast.makeText(getApplicationContext()," Right swap happens", Toast.LENGTH_SHORT).show();

                   // ReadNumber2();
                }
                else if (-velocityY <- 50.0f){
                    Toast.makeText(getApplicationContext()," down swap happens", Toast.LENGTH_SHORT).show();
                    backToMenu();
                }
                else if (velocityY <- 50.0f){
                    Toast.makeText(getApplicationContext()," up swap happens", Toast.LENGTH_SHORT).show();
                    ReadNumber();
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
    }

    private void ReadNumber2() {
       // getMessage();
        //ReadNumber1(message);
    }

    private void ReadNumber() {
        Intent intent=new Intent(this,MessageNumberRead.class);
        startActivity(intent);
    }

    private void CheckInputType() {
        if(number6==1){
            if(number5==1 && number3==1 && number4==1){
                PhoneNumberSearch();
            }
            LetterFind();
        }
    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
    private void backToMenu() {
        Intent intent=new Intent(this,Panivida_2.class);
        startActivity(intent);
        this.finish();
    }

    public boolean onTouchEvent(MotionEvent event){
        return gestureDetector.onTouchEvent(event);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_panividaya__arabanna, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        // Get current time in nano seconds.
        pressTime = System.currentTimeMillis();

        // If double click...
        if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {
            mediaPlayer.release();
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
            destroy();
            vibtare();
        }else{
            mediaPlayer.release();
            switch (v.getId()) {
                case R.id.button8:
                    mediaPlayer=MediaPlayer.create(this,R.raw.one);
                    mediaPlayer.start();
                    break;
                case R.id.button10:
                    mediaPlayer=MediaPlayer.create(this,R.raw.two);
                    mediaPlayer.start();
                    break;
                case R.id.button12:
                    mediaPlayer=MediaPlayer.create(this,R.raw.three);
                    mediaPlayer.start();
                    break;
                case R.id.button9:
                    mediaPlayer=MediaPlayer.create(this,R.raw.four);
                    mediaPlayer.start();
                    break;
                case R.id.button11:
                    mediaPlayer=MediaPlayer.create(this,R.raw.five);
                    mediaPlayer.start();
                    break;
                case R.id.button13:
                    mediaPlayer=MediaPlayer.create(this,R.raw.six);
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

    protected void ReadNumber1(String string) {
        textToSpeech.speak(string, TextToSpeech.QUEUE_FLUSH, null);
        // textToSpeech.stop();
    }

    @Override
    public void onInit(int status) {
        textToSpeech.setLanguage(Locale.UK);
        textToSpeech.setSpeechRate(0.7f);
    }
}
