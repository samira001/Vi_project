package blindhelp.visualimparied;

import android.content.Context;
import android.content.Intent;
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

import java.security.ProtectionDomain;


public class EditName extends ContactSuperClass implements View.OnClickListener{

    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button Button4;
    private MediaPlayer mediaPlayer;
    protected String myid;
    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    long pressTime;
    private long lastPressTime;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);
        getSupportActionBar().hide();// this is the code for the hide applicatoin name
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Button1 = (Button) findViewById(R.id.button4);
        Button2 = (Button) findViewById(R.id.button3);
        Button3 = (Button) findViewById(R.id.button2);
        Button4 = (Button) findViewById(R.id.button);

        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
        Button3.setOnClickListener(this);
        Button4.setOnClickListener(this);
        onBackPressed();

        mediaPlayer=MediaPlayer.create(this,R.raw.add_contact);
        mediaPlayer.start();

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);
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
                    readLetters();
                    break;
                case R.id.button3:
                    readContacts();
                    break;
                case R.id.button2:
                    SaveName();
                    break;
                case R.id.button:
                    backToMainMenu();
                    break;
            }
        } else {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:name();break;
                case R.id.button3:numbers();break;
                case R.id.button2:save();break;
                case R.id.button:backmenu();break;
            }
            lastPressTime = pressTime;
        }
    }

    private void name() {
        mediaPlayer=MediaPlayer.create(this,R.raw.name);
        mediaPlayer.start();
    }

    private void numbers() {
        mediaPlayer=MediaPlayer.create(this,R.raw.save_number);
        mediaPlayer.start();
    }

    private void save() {
        mediaPlayer=MediaPlayer.create(this,R.raw.save);
        mediaPlayer.start();
    }

    private void resetPlayer() {
        mediaPlayer.release();
    }

    private void backmenu() {
        mediaPlayer= MediaPlayer.create(this,R.raw.back);
        mediaPlayer.start();
    }
    private void SaveName() {
        addNewContact(PhoneNumber,message);
    }

    private void readContacts() {
        Intent intent=new Intent(this,NumberRead.class);
        startActivity(intent);
    }

    private void readLetters() {
        Intent intent = new Intent(this,LetterRead.class);
        startActivity(intent);
    }

    private void backToMainMenu() {
        Intent intent = new Intent(this,FirstMenu.class);
        startActivity(intent);
        this.finish();
    }
}
