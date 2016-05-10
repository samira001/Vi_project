package blindhelp.visualimparied;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.BatteryManager;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Locale;

import blindhelp.visualimparied.R;

public class TimeDate extends ActionBarActivity implements View.OnClickListener, TextToSpeech.OnInitListener {
    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button Button4;
    private MediaPlayer mediaPlayer;
    private TextToSpeech textToSpeech;
    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    long pressTime;
    private long lastPressTime;
    String batteryPct;
    String lsNow;
    String lsTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_date);
        getSupportActionBar().hide();// this is the code for the hide applicatoin name
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mediaPlayer=new MediaPlayer();
        textToSpeech=new TextToSpeech(this,this);
        onBackPressed();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);

        Button1 = (Button) findViewById(R.id.button4);
        Button2 = (Button) findViewById(R.id.button3);
        Button3 = (Button) findViewById(R.id.button2);
        Button4 = (Button) findViewById(R.id.button);


        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
        Button3.setOnClickListener(this);
        Button4.setOnClickListener(this);

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = this.registerReceiver(null, ifilter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        batteryPct = String.valueOf((level / (float) scale) * 100);

        Time dtNow = new Time();
        dtNow.setToNow();
        lsNow = dtNow.format("%Y.%m.%d %H:%M");
        lsTime = dtNow.format("%H:%M");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_time_date, menu);
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
        pressTime = System.currentTimeMillis();
        // If double click...
        if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:
                    Date();
                    break;
                case R.id.button3:
                    Time1();
                    break;
                case R.id.button2:
                    Bt_lvl();
                    break;
                case R.id.button:
                    back();
                    break;
            }
        } else {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:date();break;
                case R.id.button3:time();break;
                case R.id.button2:bt_lvl();break;
                case R.id.button:Back();break;

            }
            lastPressTime = pressTime;
        }
    }

    private void Bt_lvl() {
        textToSpeech.speak( batteryPct, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void Time1() {
        textToSpeech.speak(lsTime, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void Date() {
        textToSpeech.speak(lsNow, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void bt_lvl() {
        mediaPlayer=MediaPlayer.create(this,R.raw.bettry_content);
        mediaPlayer.start();
    }

    private void time() {
        mediaPlayer=MediaPlayer.create(this,R.raw.time);
        mediaPlayer.start();
    }

    private void date() {
        mediaPlayer=MediaPlayer.create(this,R.raw.date);
        mediaPlayer.start();
    }

    private void resetPlayer(){
        mediaPlayer.release();
    }

    private void back() {
        Intent intent=new Intent(this,secondMenu.class);
        startActivity(intent);
        this.finish();
    }

    private void Back() {
        mediaPlayer=MediaPlayer.create(this,R.raw.back);
        mediaPlayer.start();
    }

    @Override
    public void onInit(int status) {
        textToSpeech.setLanguage(Locale.ENGLISH);
        textToSpeech.setSpeechRate(0.7f);
    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
}
