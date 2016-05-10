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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;


public class MessageNumberRead extends LetterInput implements View.OnClickListener {

    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    private Button b1;
    private Button b2;
    private Button b4;
    private Button b5;
    private long lastPressTime;
    private int myNumber;
    private String PhoneNumber = null;
    Vibrator v;
    private boolean DoubleClickHappens = false;
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
        ButtonPress();
        onBackPressed();
       // getMessage();
       // message="yes";
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (velocityX < -400.0f) {
                    Toast.makeText(getApplicationContext(), " left swap happens", Toast.LENGTH_SHORT).show();
                    numberSearch();
                    //ReadNumber();
                } else if (-velocityX < -400.0f) {
                    Toast.makeText(getApplicationContext(), " Right swap happens", Toast.LENGTH_SHORT).show();
                    //deleteNumber();
                } else if (-velocityY < -10.0f) {
                    Toast.makeText(getApplicationContext(), " down swap happens", Toast.LENGTH_SHORT).show();
                    backToMenu();
                }
                else if (velocityY < -10.0f) {
                    Toast.makeText(getApplicationContext(), " up swap happens", Toast.LENGTH_SHORT).show();
                    Message();
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
    public void Message() {
        if (PhoneNumber.length() == 10) {
          // Toast.makeText(getApplicationContext(),super.PhoneNumber, Toast.LENGTH_SHORT).show();
            super.sendMessage(PhoneNumber);
        }

    }

    private void backToMenu() {
        Intent intent = new Intent(this, Panividaya_Arabanna.class);
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

            switch (v.getId()) {
                case R.id.button5:
                    number1 = 1;
                    Toast.makeText(getApplicationContext(), "Double Click Event number 1 clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button7:
                    number2 = 1;
                    Toast.makeText(getApplicationContext(), "Double Click Event number 2 clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button6:
                    number3 = 1;
                    Toast.makeText(getApplicationContext(), "Double Click Event number 4 clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button8:
                    number4 = 1;
                    Toast.makeText(getApplicationContext(), "Double Click Event number 5 clicked", Toast.LENGTH_SHORT).show();
                    break;

            }
            vibtare();
        }
        lastPressTime = pressTime;
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
}
