package com.example.nesrine.projetmobile;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nesrine on 27/06/2017.
 */

public class SharedPrefRdv {
    private Context context;

    public SharedPrefRdv(Context context) {
        this.context = context;
    }

    public boolean saveRdv(int i,String status)
    {
        SharedPreferences pref=context.getSharedPreferences("rdv",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(Integer.toString(i),status);
        return editor.commit();
    }

    public  String getRdvStatus(int i) {
        SharedPreferences pref = context.getSharedPreferences("rdv", Context.MODE_PRIVATE);

        return pref.getString(Integer.toString(i), null);

    }
}
