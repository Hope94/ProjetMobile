package com.example.nesrine.projetmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListRendezVous extends AppCompatActivity {
    private RecyclerView recyclerView;
    Toolbar toolbar;
    private List<ObjetRendezVous> listRendezVous = new ArrayList<ObjetRendezVous>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rendez_vous);
        ajouterRendezVous();
        recyclerView = (RecyclerView) findViewById(R.id.rvRendezVous);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RendezVousAdapter(listRendezVous));
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Liste des rendez-vous");


    }
    private void ajouterRendezVous() {
        listRendezVous.add(new ObjetRendezVous("Nesrine99","Sam, 22 avril 2017","9:00 - 12:00","No validé"));
        listRendezVous.add(new ObjetRendezVous("Imène11","Dim, 23 avril 2017","12:00 - 16:00","No validé"));
        listRendezVous.add(new ObjetRendezVous("Meriem87","Mar, 25 avril 2017","9:00 - 12:00","No validé"));
        listRendezVous.add(new ObjetRendezVous("Arhab99","Sam, 26 avril 2017","9:00 - 12:00","No validé"));
    }
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
