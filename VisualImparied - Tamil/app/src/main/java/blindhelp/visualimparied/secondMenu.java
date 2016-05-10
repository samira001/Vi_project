package blindhelp.visualimparied;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.content.BroadcastReceiver;
import android.os.BatteryManager;
import android.widget.Toast;

import java.util.Locale;

public class secondMenu extends ActionBarActivity implements View.OnClickListener,TextToSpeech.OnInitListener {
    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button Button4;
    private Button Button5;
    private MediaPlayer mediaPlayer;
    private TextToSpeech textToSpeech;
    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    long pressTime;
    private long lastPressTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_menu);
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
        Button5 = (Button) findViewById(R.id.button1);

        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
        Button3.setOnClickListener(this);
        Button4.setOnClickListener(this);
        Button5.setOnClickListener(this);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }
    public void PeraMenuwa(){
        mediaPlayer=MediaPlayer.create(this,R.raw.back);
        mediaPlayer.start();
    }
    public void Ewathwanna() {
        mediaPlayer=MediaPlayer.create(this,R.raw.exit);
        mediaPlayer.start();
    }

    private void Kiyawanna() {
        mediaPlayer=MediaPlayer.create(this,R.raw.bettry_content);
        mediaPlayer.start();
    }

    private void Profile() {
        mediaPlayer=MediaPlayer.create(this,R.raw.profile);
        mediaPlayer.start();
    }

    private void recording() {
        mediaPlayer=MediaPlayer.create(this,R.raw.record);
        mediaPlayer.start();
    }
    private void resetPlayer(){
        mediaPlayer.release();
    }
    @Override
    public void onClick(View v) {
        pressTime = System.currentTimeMillis();
        // If double click...
        if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:
                    OpenFirstmenu();
                    break;
                case R.id.button3:
                    Recording();
                    break;
                case R.id.button2:
                    profile();
                    break;
                case R.id.button:
                    Reading();
                    break;
                case R.id.button1:
                    Back();
                    break;

            }
        } else {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:PeraMenuwa();break;
                case R.id.button3:recording();break;
                case R.id.button2:Profile();break;
                case R.id.button:Kiyawanna();break;
                case R.id.button1:Ewathwanna();break;
            }
            lastPressTime = pressTime;
        }
    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
    private void Back() {
        //mediaPlayer.stop();
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        System.exit(0);

    }

    private void Reading() {
        Intent intent=new Intent(this,TimeDate.class);
        startActivity(intent);
        this.finish();
    }
    @Override
    public void onInit(int status) {
        textToSpeech.setLanguage(Locale.ENGLISH);
        textToSpeech.setSpeechRate(0.8f);
    }

    private void profile() {
        Intent intent=new Intent(this,Profile.class);
        startActivity(intent);
        this.finish();
    }

    private void Recording() {
        Intent intent=new Intent(this,Recordings.class);
        startActivity(intent);
        this.finish();
    }

    private void OpenFirstmenu() {
        Intent intent=new Intent(this,FirstMenu.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second_menu, menu);
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
}
