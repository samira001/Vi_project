package blindhelp.visualimparied;

import android.content.Context;
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

import blindhelp.visualimparied.R;

public class NumberPadSelect extends ActionBarActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private Button button3;
    MediaPlayer mediaplayer;
    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    long pressTime;
    private long lastPressTime;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_pad_select);
        getSupportActionBar().hide();// this is the code for the hide applicatoin name
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);
        mediaplayer=MediaPlayer.create(this,R.raw.selecttheinputmethod);
        mediaplayer.start();

        button1=(Button)findViewById(R.id.button2);
        button2=(Button)findViewById(R.id.button);
        button3=(Button)findViewById(R.id.button1);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        onBackPressed();
    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_number_pad_select, menu);
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
                case R.id.button: NumberInput();break;
                case R.id.button1:back();break;
                case R.id.button2:BrailSystem();break;
            }
        } else {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button2:brailsystem();break;
                case R.id.button1:backmenu();break;
                case R.id.button:numberPad();break;
            }
            lastPressTime = pressTime;
        }
    }

    private void resetPlayer() {
        mediaplayer.release();
    }

    private void numberPad() {
        mediaplayer= MediaPlayer.create(this,R.raw.number);
        mediaplayer.start();
    }

    private void backmenu() {
        mediaplayer= MediaPlayer.create(this,R.raw.back);
        mediaplayer.start();
    }

    private void brailsystem() {
        mediaplayer= MediaPlayer.create(this,R.raw.brail_system);
        mediaplayer.start();
    }

    private void NumberInput() {
        Intent intent=new Intent(this,NUmberInput.class);
        startActivity(intent);
        this.finish();
    }

    private void BrailSystem() {
        Intent intent=new Intent(this,CallMenu.class);
        startActivity(intent);
        //mediaplayer.stop();
        this.finish();
    }

    private void back() {
        Intent intent=new Intent(this,CallLogMain.class);
        startActivity(intent);
        this.finish();
    }
}
