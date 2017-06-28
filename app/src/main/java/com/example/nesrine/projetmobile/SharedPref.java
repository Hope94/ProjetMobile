package com.example.nesrine.projetmobile;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nesrine on 16/04/2017.
 */

public class SharedPref {
    private Context context;
    private String file;

    public SharedPref(Context context,String filename) {
        this.context = context;
        this.file=filename;
    }

    public boolean saveConnectUser(String userName)
    {

        SharedPreferences pref=context.getSharedPreferences(file,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putBoolean("connected",true);
        editor.putString("username",userName);
        return editor.commit();
    }
    public  boolean isConnected(){
        SharedPreferences pref=context.getSharedPreferences(file,Context.MODE_PRIVATE);
        return  pref.getBoolean("connected",false);

    }
    public  String getUserConnected() {
        SharedPreferences pref = context.getSharedPreferences(file, Context.MODE_PRIVATE);

        return pref.getString("username", null);

    }


}
