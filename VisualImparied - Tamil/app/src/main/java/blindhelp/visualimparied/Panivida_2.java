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


public class Panivida_2 extends ActionBarActivity implements View.OnClickListener{

    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button Button4;
    MediaPlayer mediaPlayer;
    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    long pressTime;
    private long lastPressTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panivida_2);
        getSupportActionBar().hide();// this is the code for the hide applicatoin name
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
         Button();
        mediaPlayer=new MediaPlayer();
        mediaPlayer=MediaPlayer.create(this,R.raw.start_message);
        mediaPlayer.start();
        onBackPressed();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);
    }
    public void Button(){
        Button1 = (Button) findViewById(R.id.button4);
        Button2 = (Button) findViewById(R.id.button3);
        Button3 = (Button) findViewById(R.id.button2);
        Button4 = (Button) findViewById(R.id.button);

        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
        Button3.setOnClickListener(this);
        Button4.setOnClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_panivida_2, menu);
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
                    PanividayaArabanna1();
                    break;
                case R.id.button3:
                    paminiPanivida1();
                    break;
                case R.id.button2:
                    break;
                case R.id.button:PeraMenuwa1();break;
            }
        } else {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:PanividayaArabanna();break;
                case R.id.button3:paminiPanivida();break;
                case R.id.button2:deleteMessage();break;
                case R.id.button:PeraMenuwa();break;
            }
            lastPressTime = pressTime;
        }
    }

    private void resetPlayer() {
        mediaPlayer.release();
    }

    private void deleteMessage() {
        mediaPlayer= MediaPlayer.create(this, R.raw.delete_message);
        mediaPlayer.start();
    }

    private void paminiPanivida() {
        mediaPlayer= MediaPlayer.create(this, R.raw.recv_msg);
        mediaPlayer.start();
    }
    private void paminiPanivida1() {
        Intent intent =new Intent(this,Pamini_Panivida.class);
        startActivity(intent);
        this.finish();
    }

    private void PanividayaArabanna() {
        mediaPlayer= MediaPlayer.create(this, R.raw.startmsg);
        mediaPlayer.start();
    }
    private void PanividayaArabanna1() {
        Intent intent =new Intent(this,Panividaya_Arabanna.class);
        startActivity(intent);
        this.finish();
    }
    private void PeraMenuwa(){
        mediaPlayer= MediaPlayer.create(this, R.raw.back);
        mediaPlayer.start();
    }
    private void PeraMenuwa1(){
        Intent intent =new Intent(this,FirstMenu.class);
        startActivity(intent);
        this.finish();
    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
}
