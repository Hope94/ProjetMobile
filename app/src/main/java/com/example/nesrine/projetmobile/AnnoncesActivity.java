package com.example.nesrine.projetmobile;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AnnoncesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Logement> logements = new ArrayList<>();
    private  LogementAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonces);
        setContentView(R.layout.activity_annonces);
        ajouterLogement();
        recyclerView = (RecyclerView) findViewById(R.id.rvAnnonces);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         adapter = new LogementAdapter(logements);

        recyclerView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        /*************SPINNER TYPE *****/
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item);
        spinnerAdapter.add("Appartement");
        spinnerAdapter.add("Studio");
        spinnerAdapter.add("Villa");
        spinnerAdapter.add("Duplex");
        spinner.setAdapter(spinnerAdapter);


        /*************SPINNER REGION *****/
        Spinner spinnerRegion = (Spinner) findViewById(R.id.spinnerRegion);
        ArrayAdapter<String> spinnerRegionAdapter =
                new ArrayAdapter<>(this, R.layout.spinner_item);
        spinnerRegionAdapter.add("ALGER");
        spinnerRegionAdapter.add("ANNABA");
        spinnerRegionAdapter.add("ORAN");

        spinnerRegion.setAdapter(spinnerRegionAdapter);


    }
    private void ajouterLogement() {
        logements.add(new Logement("00001","Appartement","ALGER",75000,"",R.drawable.a1));
        logements.add(new Logement("00002","Villa","ANNABA",88000,"",R.drawable.a2));
        logements.add(new Logement("00003","Appartement","ALGER",32000,"",R.drawable.a1));
        logements.add(new Logement("00004","Studio","ORAN",28000,"",R.drawable.a2));
        logements.add(new Logement("00005","Duplex","ANNABA",87000,"",R.drawable.a1));
        logements.add(new Logement("00006","Appartement","ORAN",64000,"",R.drawable.a1));
        logements.add(new Logement("00007","Duplex","ALGER",100000,"",R.drawable.a1));
        logements.add(new Logement("00008","Appartement","Annaba",44000,"",R.drawable.a1));
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
