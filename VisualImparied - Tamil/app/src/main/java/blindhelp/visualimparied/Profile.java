package blindhelp.visualimparied;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


public class Profile extends ActionBarActivity implements View.OnClickListener{

    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button Button4;
    private Button Button5;
    private MediaPlayer mediaPlayer;
    private AudioManager myAudioManager;
    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    long pressTime;
    private long lastPressTime;
    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();// this is the code for the hide applicatoin name
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mediaPlayer=new MediaPlayer();
        onBackPressed();
        mediaPlayer=MediaPlayer.create(this,R.raw.profile);
        mediaPlayer.start();
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
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

        myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
            dit();
            switch (v.getId()) {
                case R.id.button4:
                    Existing();
                    break;
                case R.id.button3:
                    Ringing();
                    break;
                case R.id.button2:
                    Silent();
                    break;
                case R.id.button:
                    Vibrate();
                    break;
                case R.id.button1:
                    Back();
                    break;

            }
            vib();
        } else {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:exsisting();break;
                case R.id.button3:rining();break;
                case R.id.button2:silent();break;
                case R.id.button:vibrate();break;
                case R.id.button1:back();break;
            }
            lastPressTime = pressTime;
        }
    }

    private void dit() {
        v.cancel();
    }

    private void vib() {
        v.vibrate(400);
    }

    private void back() {
        mediaPlayer=MediaPlayer.create(this,R.raw.back);
        mediaPlayer.start();
    }

    private void vibrate() {
        mediaPlayer=MediaPlayer.create(this,R.raw.vibrate);
        mediaPlayer.start();
    }

    private void silent() {
        mediaPlayer=MediaPlayer.create(this,R.raw.silence);
        mediaPlayer.start();
    }

    private void rining() {
        mediaPlayer=MediaPlayer.create(this,R.raw.general);
        mediaPlayer.start();
    }

    private void exsisting() {
        mediaPlayer=MediaPlayer.create(this,R.raw.exsisting_profile);
        mediaPlayer.start();
    }

    private void resetPlayer() {
        mediaPlayer.release();
    }
    private void Back() {
        Intent intent =new Intent(this,secondMenu.class);
        startActivity(intent);
        this.finish();
    }

    private void Vibrate() {
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        mediaPlayer=MediaPlayer.create(this,R.raw.profile_changed);
        mediaPlayer.start();
    }

    private void Silent() {
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        mediaPlayer=MediaPlayer.create(this,R.raw.profile_changed);
        mediaPlayer.start();
    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
    private void Ringing() {
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        mediaPlayer=MediaPlayer.create(this,R.raw.profile_changed);
        mediaPlayer.start();
    }

    private void Existing() {
        int mod=myAudioManager.getRingerMode();

        if(mod== AudioManager.RINGER_MODE_VIBRATE){
            mediaPlayer=MediaPlayer.create(this,R.raw.vibrate);
            mediaPlayer.start();
        }

        else if(mod==AudioManager.RINGER_MODE_NORMAL){
            mediaPlayer=MediaPlayer.create(this,R.raw.general);
            mediaPlayer.start();
        }

        else
        {
            mediaPlayer=MediaPlayer.create(this,R.raw.silence);
            mediaPlayer.start();
        }
    }
}
