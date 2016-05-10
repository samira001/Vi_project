package blindhelp.visualimparied;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Locale;

/**
 * Created by samira on 11/12/2015.
 */
public class CallHistory extends ContactSuperClass {
    String phNum,callTypeCode,strOrder;
    Cursor managedCursor;
    int number,type;
    TextToSpeech textToSpeech;
    String ClName;
    private int callcode;
    LinkedList<String> missed=new LinkedList();
    LinkedList<String> recevied=new LinkedList();
    LinkedList<String> outgoing=new LinkedList();
    int i=0,j=0,k=0;
    private Uri uriContact;
    private int nameId;
    private String nameIdString;

    protected void getCallDetails() {
        strOrder = android.provider.CallLog.Calls.DATE + " DESC";
  /* Query the CallLog Content Provider */
         managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null,
                 null, null, strOrder);
         number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
         type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        nameId=managedCursor.getColumnIndex(CallLog.Calls._ID);

        while (managedCursor.moveToNext()) {
            phNum = managedCursor.getString(number);
            callTypeCode = managedCursor.getString(type);
            nameIdString=managedCursor.getString(nameId);
            int callcode = Integer.parseInt(callTypeCode);
            switch (callcode) {
                case CallLog.Calls.MISSED_TYPE:
                    Missed();
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    Received();
                    break;
                case CallLog.Calls.OUTGOING_TYPE:
                    Outgoing();
                    break;
            }
        }
        managedCursor.close();
    }
   public void Outgoing(){

       outgoing.add(i,phNum);
       i++;
   }
    public void Missed(){

        missed.add(j,phNum);
        j++;
    }
    public void Received(){

        recevied.add(k,phNum);
        k++;
    }

    protected void MakeCall(String number){
        //Toast.makeText(getApplicationContext(),PhoneNumber, Toast.LENGTH_SHORT).show();
        Intent callIntent = new Intent(Intent.ACTION_CALL)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        callIntent.setData(Uri.parse("tel:"+number));
        callIntent.putExtra("com.android.phone.extra.slot",0); //For sim 1
        startActivity(callIntent);

        EndCallListener callListener = new EndCallListener();
        TelephonyManager mTM = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        mTM.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    private class EndCallListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if (TelephonyManager.CALL_STATE_RINGING == state) {
                Log.i("", "RINGING, number: " + incomingNumber);
            }
            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                //wait for phone to go offhook (probably set a boolean flag) so you know your app initiated the call.
                Log.i("", "OFFHOOK");
            }
            if (TelephonyManager.CALL_STATE_IDLE == state) {
                //when this state occurs, and your flag is set, restart your app
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_received_calls, menu);
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

    protected String getContactName(Context context, String number) {

        String name = null;

        // define the columns I want the query to return
        String[] projection = new String[] {
                ContactsContract.PhoneLookup.DISPLAY_NAME,
                ContactsContract.PhoneLookup._ID};

        // encode the phone number and build the filter URI
        Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));

        // query time
        Cursor cursor = context.getContentResolver().query(contactUri, projection, null, null, null);

        if(cursor != null) {
            if (cursor.moveToFirst()) {
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
                ClName=name;

            } else {
               ClName="save the number";
            }
            cursor.close();
        }
        return name;
    }
}
