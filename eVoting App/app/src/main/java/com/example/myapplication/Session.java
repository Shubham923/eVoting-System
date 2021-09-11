package com.example.myapplication;
/*SESSION
* USE METHODS OF THIS CLASS TO FETCH USER INFO AT ANY POINT IN APP
* */


import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import com.example.myapplication.User;

public class Session {

    private SharedPreferences prefs,sh;
    private SharedPreferences.Editor editor;


    public Session(Context cntx) {
        // TODO Auto-generated constructor stub

        SharedPreferences prefs = cntx.getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = prefs.edit();
        sh = cntx.getSharedPreferences("MyPref",
                0);

    }

    public void setUser(User user) {
        if(user!=null) {
            editor.clear().commit();
            editor.putString("username", user.getName()).apply();
            editor.putString("voterId", user.getVoterId()).apply();
            editor.putString("dob", user.getDob()).apply();
            editor.putString("emailId", user.getEmailId()).apply();
            editor.putInt("constId", user.getConstId()).apply();
            editor.commit();
        }
        else
        {
            editor.clear().commit();
            editor.putString("username", "").apply();
            editor.putString("voterId", "").apply();
            editor.putString("dob", "").apply();
            editor.putString("emailId", "").apply();
            editor.putInt("constId", 0).apply();
            editor.commit();
        }

    }

    public String getUsername() {
        String userName = sh.getString("username","");
        return userName;
    }

    public String getVoterId() {
        String str = sh.getString("voterId","");
        return str;
    }


    public String getDob() {
        String temp = sh.getString("dob","");
        return temp;
    }


    public String getEmailId() {
        String tmp = sh.getString("emailId","");
        return tmp;
    }

    public int getConstId() {
        int tmp = sh.getInt("constId",0);
        return tmp;
    }
}
