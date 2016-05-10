package blindhelp.visualimparied;

import android.content.Context;
import android.content.DialogInterface;
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
public class EditContact extends ContactSuperClass implements View.OnClickListener{

    public  MediaPlayer mediaPlayer;
    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    long pressTime;
    private long lastPressTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        getSupportActionBar().hide();// this is the code for the hide applicatoin name
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mediaPlayer=new MediaPlayer();
        mediaPlayer=MediaPlayer.create(this, R.raw.edit_contact);
        mediaPlayer.start();

        Button1 = (Button) findViewById(R.id.button4);
        Button2 = (Button) findViewById(R.id.button3);
        Button3 = (Button) findViewById(R.id.button2);
        Button4 = (Button) findViewById(R.id.button);

        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
        Button3.setOnClickListener(this);
        Button4.setOnClickListener(this);
        onBackPressed();

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
                    break;
                case R.id.button3:
                    openName();
                    break;
                case R.id.button2:
                    break;
                case R.id.button:
                    backToMainMenu();
                    break;

            }
        } else {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:deleteContact();break;
                case R.id.button3:newContact();break;
                case R.id.button2:ChangeNumber();break;
                case R.id.button:backmenu();break;
            }
            lastPressTime = pressTime;
        }
    }

    public void resetPlayer(){
        mediaPlayer.release();
    }

    private void ChangeNumber() {
        mediaPlayer=MediaPlayer.create(this, R.raw.change_no);
        mediaPlayer.start();
    }

    private void newContact() {
        mediaPlayer=MediaPlayer.create(this,R.raw.newcontact);
        mediaPlayer.start();
    }

    private void deleteContact() {
        mediaPlayer=MediaPlayer.create(this,R.raw.delete);
        mediaPlayer.start();
    }
    private void openName() {
        Intent intent = new Intent(this, EditName.class);
        startActivity(intent);
        this.finish();
    }
    public void backmenu(){
        mediaPlayer= MediaPlayer.create(this, R.raw.back);
        mediaPlayer.start();
    }
    private void backToMainMenu() {
        Intent intent = new Intent(this,FirstMenu.class);
        startActivity(intent);
        this.finish();
    }
}
