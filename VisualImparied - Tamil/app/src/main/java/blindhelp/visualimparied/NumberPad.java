package blindhelp.visualimparied;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Created by samira on 12/8/2015.
 */
public class NumberPad extends ActionBarActivity {
    protected int number1=0;
    protected int number2=0;
    protected int number3=0;
    protected int number4=0;
    protected int number5=0;
    protected int number6=0;
    private int myNumber;
    ArrayList<String> Pnumber;
    protected String PhoneNumber="0";
    int i=0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_call_menu, menu);
        return true;
    }

    public void Make_a_call() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(PhoneNumber));
        startActivity(callIntent);
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
    public void PhoneNumberSearch(){
        FOUND:
        {
            if (number1 == 1) {
                if (number2 == 1) {
                    if (number3 == 1 && number4 == 1) {
                        myNumber = 7;
                        SetNumber();
                        break FOUND;
                    } else if (number4 == 1) {
                        myNumber = 8;
                        SetNumber();
                        break FOUND;
                    } else if (number3 == 1) {
                        myNumber = 6;
                        SetNumber();
                        break FOUND;
                    }
                    myNumber = 2;
                    SetNumber();
                    break FOUND;
                } else if (number3 == 1) {
                    if (number4 == 1) {
                        myNumber = 4;
                        SetNumber();
                        break FOUND;
                    }
                    myNumber = 3;
                    SetNumber();
                    break FOUND;
                } else if (number4 == 1) {
                    myNumber = 5;
                    SetNumber();
                    break FOUND;
                }
                myNumber = 1;
                SetNumber();
                break FOUND;
            } else if (number2 == 1 && number3 == 1) {
                myNumber = 9;
                SetNumber();
                break FOUND;
            } else if (number3 == 1 && number4 == 1 && number2 == 1) {
                myNumber = 0;
                SetNumber();
                break FOUND;
            } else
                Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
        }
    }

    public String getPhoneNumber() {
        Iterator iter = Pnumber.iterator();
        while (iter.hasNext()) {
            PhoneNumber = PhoneNumber + iter.next();
            Toast.makeText(getApplicationContext(),PhoneNumber, Toast.LENGTH_SHORT).show();
        }
        return PhoneNumber;
    }
    public  void SetNumber(){
        Pnumber=new ArrayList<String>();
       String S =String.valueOf(myNumber);//this need to put in gesture detecting place.and then need to set mynumber again to 0;
        Pnumber.add(S);
        getPhoneNumber();
        number1=0;number2=0;number3=0;number4=0;
    }
    public  void DeleteNumber(){
        int l=PhoneNumber.length();
        Toast.makeText(getApplicationContext(),String.valueOf(l), Toast.LENGTH_SHORT).show();
        if (PhoneNumber.length() >=1)
        Pnumber.remove(l);
        Toast.makeText(getApplicationContext(),String.valueOf(l), Toast.LENGTH_SHORT).show();
        number1=0;number2=0;number3=0;number4=0;
    }
}
