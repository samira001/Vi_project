package blindhelp.visualimparied;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Pamini_Panivida extends CallHistory implements View.OnClickListener, TextToSpeech.OnInitListener {

    MediaPlayer mediaPlayer;
    private Activity mActivity;
    ArrayList<String> smsName;
    ArrayList<String> smsTxt;
    protected int x, y;
    protected int a, b;
    String sms = "";
    private Button Button1, Button2, Button3, Button4;
    private TextToSpeech textToSpeech;
    protected String Name;
    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    long pressTime;
    private long lastPressTime;
    private ContentProvider resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pamini__panivida);
        getSupportActionBar().hide();// this is the code for the hide applicatoin name
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mediaPlayer = new MediaPlayer();
        mediaPlayer=MediaPlayer.create(this,R.raw.recv_msg);
        mediaPlayer.start();
        textToSpeech = new TextToSpeech(this, this);
        setSms();
        ButtonClick();
        onBackPressed();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);

    }

    private void ButtonClick() {
        Button1 = (Button) findViewById(R.id.button4);
        Button2 = (Button) findViewById(R.id.button3);
        Button3 = (Button) findViewById(R.id.button2);
        Button4 = (Button) findViewById(R.id.button);

        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
        Button3.setOnClickListener(this);
        Button4.setOnClickListener(this);

        Button3.setEnabled(false);
        Button2.setEnabled(false);
    }

    private void setSms() {
        smsName = new ArrayList<String>();
        smsTxt = new ArrayList<String>();
        TextView view = new TextView(this);
        Uri uriSMSURI = Uri.parse("content://sms/inbox");
        Cursor cur = getContentResolver().query(uriSMSURI, null, null, null, null);
        while (cur.moveToNext()) {
            smsName.add(cur.getString(2));
            x++;
            smsTxt.add(cur.getString(12));
            y++;
        }
    }

    protected String getSmsName() {
        Name = smsName.get(a);a++;
        return Name;
    }

    protected String getSms() {
        b=a;
        b--;
        sms = smsTxt.get(b);
        return sms;
    }

    @Override
    public void onClick(View v) {
        pressTime = System.currentTimeMillis();
        // If double click...
        if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:
                    NextMessage();
                    break;
                case R.id.button3:
                    ReadMessage();
                    break;
                case R.id.button2:
                    ReplyMessage();
                    break;
                case R.id.button:
                    back();
                    break;
            }
        } else {
         resetPlayer();
            textToSpeech.stop();
            switch (v.getId()) {
                case R.id.button4:next();break;
                case R.id.button3:read();break;
                case R.id.button2:reply();break;
                case R.id.button:backM();break;
            }
            lastPressTime = pressTime;
        }
    }

    private void next() {
        mediaPlayer=MediaPlayer.create(this,R.raw.next_message);
        mediaPlayer.start();
    }

    private void read() {
        mediaPlayer=MediaPlayer.create(this,R.raw.read_message);
        mediaPlayer.start();
    }

    private void reply() {
        mediaPlayer=MediaPlayer.create(this,R.raw.create_msg);
        mediaPlayer.start();
    }

    private void backM() {
        mediaPlayer=MediaPlayer.create(this,R.raw.back);
        mediaPlayer.start();
    }

    private void resetPlayer() {
        mediaPlayer.release();
    }
    private void ReplyMessage() {
        Intent intent=new Intent(this,ReplyMessage.class);
        Bundle b1=new Bundle();
        b1.putString("Name",Name);
        intent.putExtras(b1);
        startActivity(intent);
        this.finish();
    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
    private void ReadMessage() {
        getSms();
        ReadNumber(sms);
        Toast.makeText(getApplicationContext(), sms, Toast.LENGTH_SHORT).show();
    }

    private void NextMessage() {
        getSmsName();
        getContactName(getBaseContext(),Name);
        Toast.makeText(getApplicationContext(),ClName, Toast.LENGTH_SHORT).show();
        ReadNumber(ClName);

        Button3.setEnabled(true);
        Button2.setEnabled(true);
    }

    private void back() {
        Intent intent = new Intent(this, Panivida_2.class);
        startActivity(intent);
        this.finish();
    }
    protected void ReadNumber(String string) {
        textToSpeech.speak(string, TextToSpeech.QUEUE_FLUSH, null);
        // textToSpeech.stop();
    }

    @Override
    public void onInit(int status) {
        textToSpeech.setLanguage(Locale.UK);
        textToSpeech.setSpeechRate(0.7f);
    }
}