package com.example.nesrine.projetmobile;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

public class DetailLog extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //afficher le bouton retour
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.inflateMenu(R.menu.toolbar_menu);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");
        TextView date=(TextView)findViewById(R.id.date);
        //date.setText("Vendredi du 13:00 à 16:00");


       // list_dates.setAdapter(adapter);
        // Button btn_map= (Button) findViewById(R.id.map_btn);


    }
    public void onClickMap(View view){

        Intent intent=new Intent(DetailLog.this,map_logement.class);
        startActivity(intent);
    }
    public void onClickRate(View view){
         new MaterialDialog.Builder(this)
                .title(R.string.noter)
                .customView(R.layout.custom_rate_bar,false)
                .positiveText(R.string.note_button)
                .negativeText(R.string.annul_button)
                .show();
    }
    public void onClickComment(View view){

        new MaterialDialog.Builder(this)
                .title(R.string.commenter)
                .customView(R.layout.custom_comment,true)
                .positiveText(R.string.comment_button)
                .negativeText(R.string.annul_button)
                .show();
    }
    public void onClickAgenda(View view){


        new MaterialDialog.Builder(this)
                .title(R.string.dispo)
                .customView(R.layout.custom_list_date,true)
                .positiveText(R.string.rdv_button)
                .show();


    }
    public void onClickRendezVous(View view){

        Intent intent=new Intent(DetailLog.this,RendezVous.class);
        startActivity(intent);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }


}
