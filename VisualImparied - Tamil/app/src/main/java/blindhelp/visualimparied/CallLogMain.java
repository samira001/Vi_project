package blindhelp.visualimparied;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


public class CallLogMain extends ActionBarActivity implements View.OnClickListener{

    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button Button4;
    private Button Button5;
    MediaPlayer mediaPlayer;
    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    long pressTime;
    private long lastPressTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log_main);
        getSupportActionBar().hide();// this is the code for the hide applicatoin name
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mediaPlayer=new MediaPlayer();
        mediaPlayer=MediaPlayer.create(this,R.raw.calls);
        mediaPlayer.start();
        ButtonClick();
        onBackPressed();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);
    }

    private void ButtonClick() {
        Button1=(Button)findViewById(R.id.button4);
        Button2=(Button)findViewById(R.id.button3);
        Button3=(Button)findViewById(R.id.button2);
        Button4=(Button)findViewById(R.id.button);
        Button5=(Button)findViewById(R.id.button1);

        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
        Button3.setOnClickListener(this);
        Button4.setOnClickListener(this);
        Button5.setOnClickListener(this);
    }


    private void back() {
        Intent intent=new Intent(this,FirstMenu.class);
        startActivity(intent);
        this.finish();
    }

    private void Missed_calls(){
        Intent intent=new Intent(this,Missed_calls.class);
        startActivity(intent);
    }
    private void received_calls(){
        Intent intent=new Intent(this,Received_calls.class);
        startActivity(intent);
    }
    private void Outgoing_calls(){
        Intent intent=new Intent(this,Outgoing_calls.class);
        startActivity(intent);
    }

    private void OpenCallMenu() {
        Intent intent=new Intent(this,NumberPadSelect.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_call_log_main, menu);
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
            switch (v.getId()) {
                case R.id.button4:
                    OpenCallMenu();
                    break;
                case R.id.button3:
                    received_calls();
                    break;
                case R.id.button2:
                    Outgoing_calls();
                    break;
                case R.id.button:
                    Missed_calls();
                    break;
                case R.id.button1:
                    back();
                    break;

            }
        } else {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:amathanna();break;
                case R.id.button3:labunu_amathum();break;
                case R.id.button2:pera_amathum();break;
                case R.id.button:magaharunu();break;
                case R.id.button1: backmenu();
                    break;
            }
            lastPressTime = pressTime;
        }
    }
    public void resetPlayer(){
            mediaPlayer.release();
    }
    public void amathanna(){
        mediaPlayer=MediaPlayer.create(this,R.raw.call);
        mediaPlayer.start();
    }
    public void pera_amathum(){
        mediaPlayer= MediaPlayer.create(this,R.raw.calls);
        mediaPlayer.start();
    }
    public void labunu_amathum(){
        mediaPlayer=MediaPlayer.create(this,R.raw.recvd_calls);
        mediaPlayer.start();
    }
    public void magaharunu(){
        mediaPlayer= MediaPlayer.create(this,R.raw.missed_calls);
        mediaPlayer.start();
    }
    public void backmenu(){
        mediaPlayer=MediaPlayer.create(this,R.raw.back);
        mediaPlayer.start();
    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
}
