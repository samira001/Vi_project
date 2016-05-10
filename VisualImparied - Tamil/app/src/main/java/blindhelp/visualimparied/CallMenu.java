package blindhelp.visualimparied;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;


public class CallMenu extends CallHistory implements View.OnClickListener,TextToSpeech.OnInitListener {

    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    private Button b1;
    private Button b2;
    private Button b4;
    private Button b5;
    private long lastPressTime;
    private String PhoneNumber = null;
    Vibrator v;
    long pressTime;
    private GestureDetector gestureDetector;
    TextToSpeech textToSpeech;
    private MediaPlayer mediaPlayer;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_menu);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);
        onBackPressed();
        ButtonPress();
        mediaPlayer=new MediaPlayer();
        mediaPlayer=MediaPlayer.create(this, R.raw.pad_instruction1);
        mediaPlayer.start();

        textToSpeech=new TextToSpeech(this,this);
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                mediaPlayer.release();
                if (velocityX < -150.0f) {
                    numberSearch();
                    //ReadNumber();
                } else if (-velocityX < -150.0f) {
                    //deleteNumber();
                } else if (-velocityY < -50.0f) {
                    backToCallMenu();
                }else if (velocityY < -50.0f) {
                    MakeCall();
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    private void MakeCall() {
       super.MakeCall(PhoneNumber);
    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
    private void backToCallMenu() {
        Intent intent = new Intent(this,NumberPadSelect.class);
        startActivity(intent);
        this.finish();
    }

    private void numberSearch() {
        super.PhoneNumberSearch();
    }

    private void deleteNumber() {
        super.DeleteNumber();
    }

    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void ButtonPress() {

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
        destroy();
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

            }
            vibtare();
        } else {
            mediaPlayer.release();
            switch (v.getId()) {
                case R.id.button5:
                    mediaPlayer = MediaPlayer.create(this, R.raw.one);
                    mediaPlayer.start();
                    break;
                case R.id.button7:
                    mediaPlayer = MediaPlayer.create(this, R.raw.two);
                    mediaPlayer.start();
                    break;
                case R.id.button6:
                    mediaPlayer = MediaPlayer.create(this, R.raw.four);
                    mediaPlayer.start();
                    break;
                case R.id.button8:
                    mediaPlayer = MediaPlayer.create(this, R.raw.five);
                    mediaPlayer.start();
                    break;
            }
            lastPressTime = pressTime;
            destroy();
        }
    }
    private void destroy() {
        v.cancel();
    }

    private void vibtare() {
        v.vibrate(400);
    }

    protected void ReadNumber() {
        String str="zero";
        for(int i=0;i<=Pnumber.size();i++){
            str=str+Pnumber.get(i);
        }
        textToSpeech.speak(str, TextToSpeech.QUEUE_FLUSH, null);
        // textToSpeech.stop();
    }

    @Override
    public void onInit(int status) {
        textToSpeech.setLanguage(Locale.UK);
        textToSpeech.setSpeechRate(1f);
    }
}