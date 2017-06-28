package com.example.nesrine.projetmobile;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnnoncesActivity extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private List<Logement> logements = new ArrayList<>();
    private  LogementAdapter adapter;
    private  Spinner spinner;
    private Spinner spinnerRegion;
    private String type;
    private String region;
    private  String url="http://192.168.43.71:8080/getLogements";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonces);
        setContentView(R.layout.activity_annonces);
        recyclerView = (RecyclerView) findViewById(R.id.rvAnnonces);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        /*************SPINNER TYPE *****/
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item);
        spinnerAdapter.add("Villa");
        spinnerAdapter.add("Appartement");
        spinnerAdapter.add("Studio");
        spinnerAdapter.add("Duplex");
        spinner.setAdapter(spinnerAdapter);


        /*************SPINNER REGION *****/
        spinnerRegion = (Spinner) findViewById(R.id.spinnerRegion);
        ArrayAdapter<String> spinnerRegionAdapter =
                new ArrayAdapter<>(this, R.layout.spinner_item);
        spinnerRegionAdapter.add("Alger");
        spinnerRegionAdapter.add("Hydra");

        spinnerRegion.setAdapter(spinnerRegionAdapter);

        ajouterLogement();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
               ajouterLogement();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        spinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ajouterLogement();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
       // recyclerView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    private void ajouterLogement() {

       type=spinner.getSelectedItem().toString();
        region=spinnerRegion.getSelectedItem().toString();
        RequestQueue queue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url+"?type="+type+"&region="+region, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                logements=new ArrayList<Logement>();
                Gson gson=new Gson();
                logements= Arrays.asList(gson.fromJson(jsonArray.toString(), Logement[].class));
                recyclerView.setAdapter(new LogementAdapter(logements,new LogementAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {

                        ///list item was clicked
                        Intent intent = new Intent(AnnoncesActivity.this,DetailLog.class);
                        intent.putExtra("logement",logements.get(position));
                        startActivity(intent);
                    }
                }));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(AnnoncesActivity.this,volleyError.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });
        queue.add(jsonArrayRequest);

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
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
            recyclerView.setAdapter(adapter);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

        }
    }

}
