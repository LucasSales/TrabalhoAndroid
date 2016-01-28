package br.ufc.quixada.dsdm.myapplicationtestemulttabs.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

/**
 * Created by viniciusthiengo on 8/23/15.
 */
public class Message {
    public static final String METHOD_SAVE = "save-message";
    public static final String METHOD_REMOVE = "remove-message";
    public static final String METHOD_LOAD_OLD = "load-old-messages";
    public static final String METHOD_GET = "get-messages";


    public static final String MESSAGE_KEY = "br.com.thiengo.gcmexample.domain.Message.MESSAGE_KEY";
    public static final String MESSAGES_SUMMARY_KEY = "br.com.thiengo.gcmexample.domain.Message.MESSAGES_SUMMARY_KEY";

    private User userFrom;
    private User userTo;
    private String message;
    private double latitude;
    private double longitude;


    public Message() {}
    public Message(User userFrom, String message,User userTo ,double latitude, double longitude) {
        this.userFrom = userFrom;
        this.message = message;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userTo = userTo;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getRegTimeForHuman(){
        String aux = "";
        Calendar c = Calendar.getInstance();

        aux += c.get(Calendar.DAY_OF_MONTH) < 10 ? "0"+c.get(Calendar.DAY_OF_MONTH) : c.get(Calendar.DAY_OF_MONTH);
        aux += "/";
        aux += c.get(Calendar.MONTH) < 10 ? "0"+c.get(Calendar.MONTH) : c.get(Calendar.MONTH);
        aux += "/";
        aux += c.get(Calendar.YEAR);
        aux += " às ";
        aux += c.get(Calendar.HOUR_OF_DAY) < 10 ? "0"+c.get(Calendar.HOUR_OF_DAY) : c.get(Calendar.HOUR_OF_DAY);
        aux += "h";
        aux += c.get(Calendar.MINUTE) < 10 ? "0"+c.get(Calendar.MINUTE) : c.get(Calendar.MINUTE);

        return( aux );
    }
}
