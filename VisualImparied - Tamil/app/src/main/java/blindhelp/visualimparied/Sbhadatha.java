package blindhelp.visualimparied;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Locale;


public class Sbhadatha extends ContactSuperClass implements View.OnClickListener,TextToSpeech.OnInitListener {

    TextToSpeech textToSpeech;
    String Number;
    MediaPlayer mediaPlayer;
    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    long pressTime;
    private long lastPressTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbhadatha);
        getSupportActionBar().hide();// this is the code for the hide applicatoin name
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.setContact();
        mediaPlayer=new MediaPlayer();
        textToSpeech=new TextToSpeech(this,this);
        mediaPlayer=MediaPlayer.create(this,R.raw.contacts);
        mediaPlayer.start();
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
        onBackPressed();

        Button2.setEnabled(false);
        Button3.setEnabled(false);
        Button4.setEnabled(false);
    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
    @Override
    public void onClick(View v) {
        pressTime = System.currentTimeMillis();
        // If double click...
        if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:
                    getContact1();
                    break;
                case R.id.button3:
                    makeCall();
                    break;
                case R.id.button2:
                    MakeMessage();
                    break;
                case R.id.button:
                    editContact();
                    break;
                case R.id.button1:
                    backToMainMenu();
                    break;

            }
        } else {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:nextContact();break;
                case R.id.button3:call();break;
                case R.id.button2:sms();break;
                case R.id.button:edit();break;
                case R.id.button1:backmenu();break;
            }
            lastPressTime = pressTime;
        }
    }

    private void sms() {
        mediaPlayer=MediaPlayer.create(this,R.raw.start_message);
        mediaPlayer.start();
    }

    private void call() {
        mediaPlayer=MediaPlayer.create(this,R.raw.call);
        mediaPlayer.start();
    }

    private void edit() {
        mediaPlayer=MediaPlayer.create(this,R.raw.edit_contact);
        mediaPlayer.start();
    }

    private void nextContact() {
        mediaPlayer=MediaPlayer.create(this, R.raw.searchcont);
        mediaPlayer.start();
    }

    private void resetPlayer() {
        mediaPlayer.release();
    }

    public void makeCall(){
        super.MakeCall(Cnumber);
    }
    public void backmenu(){
        mediaPlayer=MediaPlayer.create(this,R.raw.back);
        mediaPlayer.start();
    }
  /*  public void nextContact(){
        mediaPlayer=MediaPlayer.create(this,R.raw.soyanna);
        mediaPlayer.start();
    }*/
    private void MakeMessage() {
        Intent intent=new Intent(this,LetterRead2.class);
        Bundle b1=new Bundle();
        b1.putString("Name",Cnumber);
        intent.putExtras(b1);
        startActivity(intent);
    }

    private void getContact1() {
        super.getContact();
        Toast.makeText(getApplicationContext(),Cname+" "+Cnumber, Toast.LENGTH_SHORT).show();
        ReadNumber();

        Button2.setEnabled(true);
        Button3.setEnabled(true);
        Button4.setEnabled(true);
    }

    private void editContact() {
        Intent intent = new Intent(this,EditContact.class);
        startActivity(intent);
        this.finish();
    }

    private void backToMainMenu() {
        Intent intent = new Intent(this,FirstMenu.class);
        startActivity(intent);
        this.finish();
    }
    protected void ReadNumber(){
        textToSpeech.speak(Cname, TextToSpeech.QUEUE_FLUSH, null);
        // textToSpeech.stop();
    }

    @Override
    public void onInit(int status) {
        textToSpeech.setLanguage(Locale.UK);
        textToSpeech.setSpeechRate(0.7f);
    }
}
