package blindhelp.visualimparied;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.Locale;
import java.util.Random;


public class Missed_calls extends CallHistory implements TextToSpeech.OnInitListener, View.OnClickListener{

    private Button next_num;
    private Button mke_call;
    private Button snd_msg;
    private Button sv_num;
    private Button back;
    protected String Phnumber;
    int i=0;
    TextToSpeech textToSpeech;
    MediaPlayer mediaPlayer;
    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    long pressTime;
    private long lastPressTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missed_calls);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        textToSpeech=new TextToSpeech(this,this);
        super.getCallDetails();
        onBackPressed();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);
        mediaPlayer=MediaPlayer.create(this,R.raw.missed_calls);
        mediaPlayer.start();

        next_num = (Button) findViewById(R.id.button4);
        mke_call = (Button) findViewById(R.id.button3);
        snd_msg = (Button) findViewById(R.id.button2);
        sv_num = (Button) findViewById(R.id.button);
        back = (Button) findViewById(R.id.button1);

        next_num.setOnClickListener(this);
        mke_call.setOnClickListener(this);
        snd_msg.setOnClickListener(this);
        sv_num.setOnClickListener(this);
        back.setOnClickListener(this);

        mke_call.setEnabled(false);
        snd_msg.setEnabled(false);
        sv_num.setEnabled(false);

    }
    private void Back_to_menu() {
        Intent intent=new Intent(this,CallLogMain.class);
        startActivity(intent);
        this.finish();
    }

    private void CheckMissedCalls() {
            Phnumber = missed.get(i);
            i++;
            getContactName(getBaseContext(), Phnumber);
            Toast.makeText(getApplicationContext(),Phnumber+" "+ClName, Toast.LENGTH_SHORT).show();
            ReadNumber();

        mke_call.setEnabled(true);
        snd_msg.setEnabled(true);
        sv_num.setEnabled(true);
    }
    protected void ReadNumber(){
        mediaPlayer.release();
        textToSpeech.speak(ClName, TextToSpeech.QUEUE_FLUSH, null);

    }

    @Override
    public void onInit(int status) {
        textToSpeech.setLanguage(Locale.UK);
        textToSpeech.setSpeechRate(1f);
    }

    @Override
    public void onClick(View v) {
        pressTime = System.currentTimeMillis();
        // If double click...
        if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:
                    CheckMissedCalls();break;
                case R.id.button3:
                    makeCall();
                    break;
                case R.id.button2:
                    makeSms();
                    break;
                case R.id.button:
                    saveNumber();
                    break;
                case R.id.button1:
                    Back_to_menu();
                    break;

            }
        } else {
            resetPlayer();
            textToSpeech.stop();
            switch (v.getId()) {
                case R.id.button4:nextnum();break;
                case R.id.button3:call();break;
                case R.id.button2:sms();break;
                case R.id.button:save();break;
                case R.id.button1:back();break;
            }
            lastPressTime = pressTime;
        }
    }

    private void nextnum() {
        mediaPlayer=MediaPlayer.create(this,R.raw.next_call);
        mediaPlayer.start();
    }

    private void call() {
        mediaPlayer=MediaPlayer.create(this,R.raw.call);
        mediaPlayer.start();
    }

    private void sms() {
        mediaPlayer=MediaPlayer.create(this,R.raw.start_message);
        mediaPlayer.start();
    }

    private void save() {
        mediaPlayer=MediaPlayer.create(this,R.raw.save_number);
        mediaPlayer.start();
    }

    private void back() {
        mediaPlayer=MediaPlayer.create(this, R.raw.back);
        mediaPlayer.start();
    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
    private void resetPlayer(){
        mediaPlayer.release();
    }

    private void saveNumber() {
        Intent intent=new Intent(this,LetterInput_Contactsave.class);
        Bundle b1=new Bundle();
        b1.putString("Name",Phnumber);
        intent.putExtras(b1);
        startActivity(intent);
        this.finish();
    }

    private void makeSms() {
        Intent intent=new Intent(this,LetterRead2.class);
        Bundle b1=new Bundle();
        b1.putString("Name",Phnumber);
        intent.putExtras(b1);
        startActivity(intent);
        this.finish();
    }

    private void makeCall() {
        super.MakeCall(Phnumber);
    }
}
