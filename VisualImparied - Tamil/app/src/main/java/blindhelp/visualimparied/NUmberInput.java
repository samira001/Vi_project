package blindhelp.visualimparied;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import blindhelp.visualimparied.R;

public class NUmberInput extends CallHistory implements View.OnClickListener {

    MediaPlayer mediaplayer;
    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    long pressTime;
    private long lastPressTime;
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, button11, button12;
    protected String PhoneNumber= "0";
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_input);
        getSupportActionBar().hide();// this is the code for the hide applicatoin name
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mediaplayer = new MediaPlayer();
        mediaplayer=MediaPlayer.create(this,R.raw.numberinput);
        mediaplayer.start();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(10);

        button1 = (Button) findViewById(R.id.button7);
        button2 = (Button) findViewById(R.id.button4);
        button3 = (Button) findViewById(R.id.button1);
        button4 = (Button) findViewById(R.id.button8);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button2);
        button7 = (Button) findViewById(R.id.button9);
        button8 = (Button) findViewById(R.id.button6);
        button9 = (Button) findViewById(R.id.button3);
        button0 = (Button) findViewById(R.id.button);
        button11 = (Button) findViewById(R.id.button14);
        button12 = (Button) findViewById(R.id.button16);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button0.setOnClickListener(this);
        button11.setOnClickListener(this);
        button12.setOnClickListener(this);
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        // do nothing.
    }

    protected void Call() {
        //Toast.makeText(getApplicationContext(),PhoneNumber, Toast.LENGTH_SHORT).show();
      super.MakeCall(PhoneNumber);
    }

    @Override
    protected void onUserLeaveHint(){
        Intent intent=new Intent(this,FirstMenu.class);
        startActivity(intent);
        this.finish();
    }
    @Override
    public void onClick(View v) {
        pressTime = System.currentTimeMillis();
        // If double click...
        if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {
             resetPlayer();
            switch (v.getId()) {
                case R.id.button1:numberSet("1");Toast.makeText(getApplicationContext(),"1 select", Toast.LENGTH_SHORT).show(); break;
                case R.id.button2:numberSet("2");Toast.makeText(getApplicationContext(),"2 select",Toast.LENGTH_SHORT).show(); break;
                case R.id.button3:numberSet("3");Toast.makeText(getApplicationContext(),"3 select",Toast.LENGTH_SHORT).show();break;
                case R.id.button4:numberSet("4");Toast.makeText(getApplicationContext(),"4 select",Toast.LENGTH_SHORT).show();break;
                case R.id.button5:numberSet("5");Toast.makeText(getApplicationContext(),"5 select",Toast.LENGTH_SHORT).show();break;
                case R.id.button6:numberSet("6");Toast.makeText(getApplicationContext(),"6 select",Toast.LENGTH_SHORT).show();break;
                case R.id.button7:numberSet("7");Toast.makeText(getApplicationContext(),"7 select",Toast.LENGTH_SHORT).show();break;
                case R.id.button8:numberSet("8");Toast.makeText(getApplicationContext(),"8 select",Toast.LENGTH_SHORT).show();break;
                case R.id.button9:numberSet("9");Toast.makeText(getApplicationContext(),"9 select",Toast.LENGTH_SHORT).show();break;
                case R.id.button:numberSet("0");Toast.makeText(getApplicationContext(),"0 select",Toast.LENGTH_SHORT).show();break;
                case R.id.button14:back();break;
                case R.id.button16:Call();break;
            }
        } else {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button7:seven(); break;
                case R.id.button4:four();break;
                case R.id.button1:one();break;
                case R.id.button8:eight(); break;
                case R.id.button5:five();break;
                case R.id.button2:two();break;
                case R.id.button9:nine();break;
                case R.id.button6:six();break;
                case R.id.button3:three();break;
                case R.id.button:zero(); break;
                case R.id.button14:backmenu();break;
                case R.id.button16:call();break;
            }
            lastPressTime = pressTime;
        }
    }

    private void resetPlayer() {
        mediaplayer.release();
    }

    private void call() {
        mediaplayer=MediaPlayer.create(this,R.raw.call);
        mediaplayer.start();
    }

    private void backmenu() {
        mediaplayer=MediaPlayer.create(this,R.raw.back);
        mediaplayer.start();
    }

    private void zero(){
        mediaplayer=MediaPlayer.create(this, R.raw.zero);
        mediaplayer.start();
    }

    private void nine() {
        mediaplayer=MediaPlayer.create(this,R.raw.nine);
        mediaplayer.start();
    }

    private void eight() {
        mediaplayer=MediaPlayer.create(this,R.raw.eight);
        mediaplayer.start();
    }

    private void seven() {
        mediaplayer=MediaPlayer.create(this,R.raw.seven);
        mediaplayer.start();
    }

    private void six() {
        mediaplayer=MediaPlayer.create(this,R.raw.six);
        mediaplayer.start();
    }

    private void five() {
        mediaplayer=MediaPlayer.create(this,R.raw.five);
        mediaplayer.start();
    }

    private void four() {
        mediaplayer=MediaPlayer.create(this,R.raw.four);
        mediaplayer.start();
    }

    private void three() {
        mediaplayer= MediaPlayer.create(this, R.raw.three);
        mediaplayer.start();
    }

    private void two() {
        mediaplayer=MediaPlayer.create(this,R.raw.two);
        mediaplayer.start();
    }

    private void one() {
        mediaplayer=MediaPlayer.create(this,R.raw.one);
        mediaplayer.start();
    }

    private void back() {
        Intent intent=new Intent(this,NumberPadSelect.class);
        startActivity(intent);
        this.finish();
    }

    private void numberSet(String str) {
        PhoneNumber= PhoneNumber+str;
        if (PhoneNumber.length()==10){

            button7.setEnabled(false);
            button4.setEnabled(false);
            button1.setEnabled(false);
            button8.setEnabled(false);
            button5.setEnabled(false);
            button2.setEnabled(false);
            button9.setEnabled(false);
            button6.setEnabled(false);
            button3.setEnabled(false);
        }
    }
}
