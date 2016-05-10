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


public class FirstMenu extends ActionBarActivity implements View.OnClickListener{

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
        setContentView(R.layout.activity_first_menu);
        getSupportActionBar().hide();// this is the code for the hide applicatoin name
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mediaPlayer=new MediaPlayer();
        instrction();
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
    }


    private void OpenNextMenu() {
        Intent intent=new Intent(this,secondMenu.class);
        startActivity(intent);
        this.finish();
    }

    private void OpenRemindermenu() {
        Intent intent=new Intent(this,Reminder.class);
        startActivity(intent);
        this.finish();
    }

    private void OpenMessageMenu() {
        Intent intent=new Intent(this,Panivida_2.class);
        startActivity(intent);
        this.finish();
    }

    private void OpenContactMenu() {
        Intent intent=new Intent(this,Sbhadatha.class);
        startActivity(intent);
        this.finish();
    }

    private void OpenCallMenu() {
        Intent intent = new Intent(this,CallLogMain.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_menu, menu);
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
    public void instrction(){
        mediaPlayer=MediaPlayer.create(this,R.raw.menu_instructions);
        mediaPlayer.start();
    }
    public void amathum(){
        mediaPlayer=MediaPlayer.create(this,R.raw.calls);
        mediaPlayer.start();
    }
    public void panivida(){
        mediaPlayer=MediaPlayer.create(this,R.raw.start_message);
        mediaPlayer.start();
    }
    public void sambandhatha(){
        mediaPlayer=MediaPlayer.create(this,R.raw.contacts);
        mediaPlayer.start();
    }
    public void sihikadaveem(){
        mediaPlayer.release();
        mediaPlayer=MediaPlayer.create(this,R.raw.reminder);
        mediaPlayer.start();
    }
    public void devenaMenuwa(){
        mediaPlayer=MediaPlayer.create(this,R.raw.second_menu);
        mediaPlayer.start();
    }
    public void resetPlayer(){
        mediaPlayer.release();
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
                    OpenCallMenu();
                    break;
                case R.id.button3:
                    OpenMessageMenu();break;
                case R.id.button2:
                    OpenContactMenu();
                    break;
                case R.id.button:
                    OpenRemindermenu();
                    break;
                case R.id.button1:
                    OpenNextMenu();
                    break;
            }
        } else {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:amathum();break;
                case R.id.button3:panivida();break;
                case R.id.button2:sambandhatha();break;
                case R.id.button:sihikadaveem();break;
                case R.id.button1:devenaMenuwa();break;
            }
            lastPressTime = pressTime;
        }
    }
}
