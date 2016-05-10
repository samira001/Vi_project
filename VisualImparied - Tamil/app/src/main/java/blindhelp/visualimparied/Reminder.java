package blindhelp.visualimparied;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

import blindhelp.visualimparied.R;

public class Reminder extends LetterInput implements View.OnClickListener {
    private Button Button1,Button2,Button3,Button4,Button5;
    private MediaPlayer mediaPlayer;
    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    long pressTime;
    private long lastPressTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        getSupportActionBar().hide();// this is the code for the hide applicatoin name
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mediaPlayer=new MediaPlayer();
        mediaPlayer=MediaPlayer.create(this,R.raw.reminder);
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
        onBackPressed();
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
                    CheckReminder();
                    Toast.makeText(getApplicationContext(), "check reminder", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button3:
                    Toast.makeText(getApplicationContext(), "time", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button2:
                    Toast.makeText(getApplicationContext(), "date", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button:
                    Toast.makeText(getApplicationContext(), "reson", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button1:
                    back();break;

            }
        } else {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:checkreminder();break;
                case R.id.button3:settime();break;
                case R.id.button2:setdate();break;
                case R.id.button:save();break;
                case R.id.button1:backaudio();break;
            }
            lastPressTime = pressTime;
        }

    }

    private void save() {
        mediaPlayer= MediaPlayer.create(this,R.raw.save);
        mediaPlayer.start();
    }

    private void setdate() {
        mediaPlayer= MediaPlayer.create(this,R.raw.date);
        mediaPlayer.start();
    }

    private void settime() {
        mediaPlayer= MediaPlayer.create(this,R.raw.time);
        mediaPlayer.start();
    }

    private void checkreminder() {
        mediaPlayer= MediaPlayer.create(this,R.raw.reminder);
        mediaPlayer.start();
    }

    private void CheckReminder(){
        String[] projection =
                new String[]{
                        CalendarContract.Calendars._ID,
                        CalendarContract.Calendars.NAME,
                        CalendarContract.Calendars.ACCOUNT_NAME,
                        CalendarContract.Calendars.ACCOUNT_TYPE};
        Cursor calCursor =
                getContentResolver().
                        query(CalendarContract.Calendars.CONTENT_URI,
                                projection,
                                CalendarContract.Calendars.VISIBLE + " = 1",
                                null,
                                CalendarContract.Calendars._ID + " ASC");
        if (calCursor.moveToFirst()) {
            do {
                long id = calCursor.getLong(0);
                String displayName = calCursor.getString(1);
                Toast.makeText(getApplicationContext(), displayName, Toast.LENGTH_SHORT).show();
            } while (calCursor.moveToNext());
        }
    }
    private void backaudio() {
        mediaPlayer=MediaPlayer.create(this,R.raw.back);
        mediaPlayer.start();
    }

    private void back() {
        Intent intent=new Intent(this,FirstMenu.class);
        startActivity(intent);
        this.finish();
    }

    private void resetPlayer() {
        mediaPlayer.release();
    }
}
