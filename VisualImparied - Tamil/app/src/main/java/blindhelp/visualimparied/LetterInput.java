package blindhelp.visualimparied;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import android.telephony.SmsManager;

/**
 * Created by samira on 12/30/2015.
 */
public class LetterInput extends NumberPad{
    protected String letter;
    ArrayList<String> txt=new ArrayList<String>();
    public static String message;
    private String messageToSend;
    private String toPhoneNumber;

    public void setSpace(){
        txt.add(" ");
    }
    public void LetterFind() {
        Found:
        {
            if (number6 == 1) {
                if (number1 == 1) {
                    if (number3 == 1) {
                        if (number5 == 1) {
                            letter = "z";
                            SetString();
                            break Found;
                        }
                        letter = "u";
                        SetString();
                        break Found;
                    }
                    if (number2 == 1 && number3 == 1) {
                        letter = "v";
                        SetString();
                        break Found;
                    } else if (number3 == 1 && number4 == 1) {
                        if (number5 == 1) {
                            letter = "y";
                            SetString();
                            break Found;
                        }
                        letter = "x";
                        SetString();
                        break Found;
                    }
                    letter = "x";
                    SetString();
                    break Found;
                }
                if (number2 == 1 && number4 == 1 && number5 == 1) {
                    letter = "w";
                    SetString();
                    break Found;
                }
            }
            //u to z letters are identified;
            if (number3 == 1) {
                if (number1 == 1) {
                    if (number4 == 1) {
                        if (number5 == 1) {
                            letter = "n";
                            SetString();
                            break Found;
                        }
                        letter = "m";
                        SetString();
                        break Found;
                    }
                    if (number5 == 1) {
                        letter = "o";
                        SetString();
                        break Found;
                    }
                    if (number2 == 1) {
                        if (number4 == 1) {
                            if (number5 == 1) {
                                letter = "q";
                                SetString();
                                break Found;
                            }
                            letter = "p";
                            SetString();
                            break Found;
                        }
                        if (number5 == 1) {
                            letter = "r";
                            SetString();
                            break Found;
                        }
                        letter = "l";
                        SetString();
                        break Found;
                    }
                    letter = "k";
                    SetString();
                    break Found;
                } else if (number2 == 1 && number3 == 1 && number4 == 1) {
                    if (number5 == 1) {
                        letter = "t";
                        SetString();
                        break Found;
                    }
                    letter = "s";
                    SetString();
                    break Found;
                }
            }//k to t letters finded
            if (number2 == 1 && number4 == 1) {
                if (number5 == 1) {
                    letter = "j";
                    SetString();
                    break Found;
                }
                letter = "i";
                SetString();
                break Found;
            }
            if (number1 == 1) {
                if (number4 == 1) {
                    if (number5 == 1) {
                        letter = "d";
                        SetString();
                        break Found;
                    }
                    letter = "c";
                    SetString();
                    break Found;
                }
                if (number5 == 1) {
                    letter = "e";
                    SetString();
                    break Found;
                }
                if (number2 == 1) {
                    if (number4 == 1) {
                        if (number5 == 1) {
                            letter = "g";
                            SetString();
                            break Found;
                        }
                        letter = "f";
                        SetString();
                        break Found;
                    }
                    if (number5 == 1) {
                        letter = "h";
                        SetString();
                        break Found;
                    }
                    letter = "b";
                    SetString();
                    break Found;
                }
                letter = "a";
                SetString();break Found;
            }//letters a to j
            if (number1==0 && number2==0 && number3==0 && number4==0 && number5==0 && number6==0){
                setSpace(); break Found;
            }
        }
    }
    public  void SetString(){
        txt.add(letter);
        getMessage();
        number1=0;number2=0;number3=0;number4=0;number5=0;number6=0;
    }
    public String getMessage() {
        message="";
        Iterator iter = txt.iterator();
        while (iter.hasNext()) {
            message = message + iter.next();
        }
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        return message;
    }
    public void sendMessage(String number){
        toPhoneNumber=number;
        messageToSend=message;
        //Toast.makeText(getApplicationContext(), messageToSend +" "+toPhoneNumber, Toast.LENGTH_SHORT).show();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(toPhoneNumber, null, messageToSend, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again later!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
