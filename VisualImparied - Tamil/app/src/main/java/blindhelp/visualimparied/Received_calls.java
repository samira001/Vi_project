package blindhelp.visualimparied;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class Received_calls extends CallHistory implements TextToSpeech.OnInitListener, View.OnClickListener{

    private Button next_num;
    private Button mke_call;
    private Button snd_msg;
    private Button sv_num;
    private Button back;
    protected String Phnumber;
    int i=0;
    private static final long DOUBLE_PRESS_INTERVAL =250; //interval for double press in millis
    long pressTime;
    private long lastPressTime;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_calls);
        getSupportActionBar().hide();// this is the code for the hide applicatoin name
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        textToSpeech=new TextToSpeech(this,this);
        super.getCallDetails();
        onBackPressed();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);
        mediaPlayer=MediaPlayer.create(this,R.raw.recvd_calls);
        mediaPlayer.start();

        next_num=(Button) findViewById(R.id.button4);
        mke_call=(Button) findViewById(R.id.button3);
        snd_msg=(Button) findViewById(R.id.button2);
        sv_num=(Button)findViewById(R.id.button);
        back=(Button) findViewById(R.id.button1);

        next_num.setOnClickListener(this);
        mke_call.setOnClickListener(this);
        snd_msg.setOnClickListener(this);
        sv_num.setOnClickListener(this);
        back.setOnClickListener(this);

        mke_call.setEnabled(false);
        snd_msg.setEnabled(false);
        sv_num.setEnabled(false);

    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
    private void make_call() {
        super.MakeCall(Phnumber);
    }

    private void Back_to_menu() {
        Intent intent = new Intent(this, CallLogMain.class);
        startActivity(intent);
        this.finish();
    }

    private void CheckReceivedCalls() {
            Phnumber=recevied.get(i);
             getContactName(getBaseContext(),Phnumber);
            Toast.makeText(getApplicationContext(),Phnumber+" "+ClName, Toast.LENGTH_SHORT).show();
            ReadNumber();
            i++;

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
        textToSpeech.setLanguage(Locale.ENGLISH);
        textToSpeech.setSpeechRate(0.7f);
    }

    @Override
    public void onClick(View v) {
        pressTime = System.currentTimeMillis();
        // If double click...
        if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:
                    CheckReceivedCalls();
                    break;
                case R.id.button3:
                    make_call();
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
                case R.id.button4:calls();break;
                case R.id.button3:call();break;
                case R.id.button2:sms();break;
                case R.id.button:save();break;
                case R.id.button1:back();break;
            }
            lastPressTime = pressTime;
        }
    }

    private void calls() {
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
        mediaPlayer=MediaPlayer.create(this,R.raw.back);
        mediaPlayer.start();
    }

    private void resetPlayer() {
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
}