package blindhelp.visualimparied;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.media.MediaRecorder;
import android.widget.Toast;

import java.io.IOException;


public class Recordings extends ActionBarActivity implements View.OnClickListener{
    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button Button4;
    private Button Button5;
    MediaRecorder myAudioRecorder;
    private String outputFile = null;
    private MediaPlayer mediaPlayer;
    private Vibrator v;
    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    long pressTime;
    private long lastPressTime;
    private String i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordings);
        getSupportActionBar().hide();// this is the code for the hide applicatoin name
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        mediaPlayer=new MediaPlayer();
        mediaPlayer=MediaPlayer.create(this,R.raw.record);
        mediaPlayer.start();
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

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";;

        myAudioRecorder=new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);

    }

    @Override
    public void onClick(View v) {
        pressTime = System.currentTimeMillis();
        // If double click...
        if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:
                    recordings();
                    break;
                case R.id.button3:
                    playRecording();
                    break;
                case R.id.button2:
                    start();
                    break;
                case R.id.button:
                    stopandSave();
                    break;
                case R.id.button1:
                    Back();
                    break;

            }
        } else {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:Recordin();break;
                case R.id.button3:play();break;
                case R.id.button2:record();break;
                case R.id.button:SaveAndStop();break;
                case R.id.button1:back();break;
            }
            lastPressTime = pressTime;
        }
    }

    private void resetPlayer() {
        mediaPlayer.release();
    }

    private void back() {
        mediaPlayer=MediaPlayer.create(this,R.raw.back);
        mediaPlayer.start();
    }

    private void play() {
        mediaPlayer=MediaPlayer.create(this, R.raw.start_play);
        mediaPlayer.start();
    }

    private void record() {
        mediaPlayer=MediaPlayer.create(this, R.raw.record);
        mediaPlayer.start();
    }

    private void Recordin() {
        mediaPlayer=MediaPlayer.create(this, R.raw.searchrecording);
        mediaPlayer.start();
    }

    private void Back() {
        Intent intent=new Intent(this,secondMenu.class);
        startActivity(intent);
        vibrate();
        this.finish();
    }

    private void stopandSave() {
        myAudioRecorder.stop();
        myAudioRecorder.release();
        myAudioRecorder  = null;

        Button1.setEnabled(true);
        Button2.setEnabled(true);
        Button5.setEnabled(true);
        Button3.setEnabled(true);
        Button4.setEnabled(true);

        Toast.makeText(getApplicationContext(), "Audio recorded successfully",Toast.LENGTH_LONG).show();
        vibrate();
    }

    protected void start() {
        try {
            myAudioRecorder.prepare();
            myAudioRecorder.start();

            vibrate();
        }

        catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void SaveAndStop() {
            mediaPlayer = MediaPlayer.create(this, R.raw.stop_and_save);
            mediaPlayer.start();
    }

    private void vibrate() {
        v.vibrate(10);
    }

    private void playRecording() {
        try {
            mediaPlayer.setDataSource(outputFile);
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mediaPlayer.prepare();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.start();
        Toast.makeText(getApplicationContext(), "Playing audio", Toast.LENGTH_LONG).show();

    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
    private void recordings() {

    }
}
