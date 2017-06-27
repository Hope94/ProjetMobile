package com.example.nesrine.projetmobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.chabbal.slidingdotsplash.SlidingSplashView;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DetailLog extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private Logement logement;
    private  TextView textSummary;
    private ImageView detailImage;
    private List<String> list;
    private List<String> dates;
    private User user;
    private String connected="FAILED";
    private String url="http://192.168.1.2:8080/getDetailLogement";
    private String url_dates="http://192.168.1.2:8080/getDates";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_log);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user=new User();


        //afficher le bouton retour
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.inflateMenu(R.menu.toolbar_menu);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");
        //TextView date=(TextView)findViewById(R.id.date);
        //date.setText("Vendredi du 13:00 à 16:00");


       // list_dates.setAdapter(adapter);
        // Button btn_map= (Button) findViewById(R.id.map_btn);
        SlidingSplashView splashView  = (SlidingSplashView) findViewById(R.id.splash);

        splashView.addOnPageChangeListener(this);
        textSummary=(TextView) findViewById(R.id.textDesciptif);
        logement = (Logement) getIntent().getSerializableExtra("logement");
        displayBasicDetail();
        getLogementDetails();
    }
    public void onClickMap(View view){

        Intent intent=new Intent(DetailLog.this,map_logement.class);
        intent.putExtra("logement",logement);
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

        RequestQueue queue = queue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url_dates+"?id_log="+logement.getId(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                DateVisites dates_visite = gson.fromJson(jsonObject.toString(), DateVisites.class);

                new MaterialDialog.Builder(DetailLog.this)
                        .title(R.string.dispo)
                        .items(dates_visite.getListDates())
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                            }
                        })
                        .positiveText(R.string.rdv_button)
                        .show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(DetailLog.this, "Erreur", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);




    }
    public void onClickRendezVous(View view){

        //Intent intent=new Intent(DetailLog.this,RendezVous.class);
        //startActivity(intent);
        if (user.getStatus().equals("connected")){
            RequestQueue queue = queue= Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url_dates+"?id_log="+logement.getId(), null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Gson gson = new Gson();
                    DateVisites dates_visite = gson.fromJson(jsonObject.toString(), DateVisites.class);
                    new MaterialDialog.Builder(DetailLog.this)
                            .title(R.string.rdv_desc)
                            .items(dates_visite.getListDates())
                            .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                                @Override
                                public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                    /**
                                     * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                                     * returning false here won't allow the newly selected radio button to actually be selected.
                                     **/


                                    return true;
                                }
                            })
                            .positiveText(R.string.rdv_btn)
                            .negativeText(R.string.annul_button)
                            .show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(DetailLog.this, "Erreur", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(jsonObjectRequest);

        }else{
            login();
        }



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
    private void displayBasicDetail(){

        TextView textPrix = (TextView) findViewById(R.id.text_prix);
        TextView textType = (TextView) findViewById(R.id.text_type);
        TextView textRegion = (TextView) findViewById(R.id.text_region);
        textPrix.setText(Integer.toString((int) logement.getPrix())+" DA");
        textRegion.setText(logement.getRegion());
        textType.setText(logement.getType());
    }

    private void getLogementDetails(){
        RequestQueue queue = queue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url+"?id_log="+logement.getId(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                Logement logementDetail = gson.fromJson(jsonObject.toString(), Logement.class);
                textSummary.setText(logementDetail.getDescriptif());
                /*Glide.with(DetailLog.this).load(logementDetail.getListDetailImages()[0])
                        .placeholder(R.drawable.ic_cloud).
                        skipMemoryCache(true).into(detailImage);*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(DetailLog.this, "Erreur", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);

    }




    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("OnPageScrolled", String.valueOf(position));
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("OnPageSelected", String.valueOf(position));

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d("PageScrollStateChanged", String.valueOf(state));
    }
    public void login(){
        Intent i = new Intent(this, Login.class);
        startActivityForResult(i, 1);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                user=(User) data.getSerializableExtra("user");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
