package com.example.nesrine.projetmobile;

/**
 * Created by Nesrine on 16/04/2017.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    private Context context;
    static final String DB_NAME ="rdvdb";
    static final int DB_VERSION = 1;
    static final String rdvCreation = "create table rendezvous r (id string primary key,status)";


    public DataBaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(rdvCreation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS book");
        onCreate(db);
    }

    public boolean addUpdate(String id_rdv,String status) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id_rdv);
        contentValues.put("status",status);

        long rowId = db.insert("rendezvous", null, contentValues);
        return (rowId!=-1);
    }

    public List<ObjetRendezVous> getRendezVousStatus() {
        List<ObjetRendezVous> listRdv = new ArrayList<ObjetRendezVous>();
        String query ="select * from rendezvous";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
             ObjetRendezVous rdv = new ObjetRendezVous();
            rdv.setId_rdv(cursor.getString(1));
            rdv.setStatus(cursor.getString(2));
            listRdv.add(rdv);
            cursor.moveToNext();
        }
        db.close();
        return listRdv;
    }

    public String getStatusById(String id_log) {
        String status=null;
        String query ="select * from book where id_log=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{id_log});
        if (cursor.moveToFirst()) {
            status=cursor.getString(2);
        }
        db.close();
        return status;
    }



}
