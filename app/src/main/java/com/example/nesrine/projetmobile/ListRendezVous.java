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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ListRendezVous extends AppCompatActivity {
    private RecyclerView recyclerView;
    private  String url="http://192.168.1.3:8080/getRendezVous";
    ProgressBar progressBar;
    Toolbar toolbar;
    private List<ObjetRendezVous> listRendezVous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rendez_vous);
        ajouterRendezVous();
        recyclerView = (RecyclerView) findViewById(R.id.rvRendezVous);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Liste des rendez-vous");


    }
    private void ajouterRendezVous() {
       // RequestFuture<JSONArray> future = RequestFuture.newFuture();
       // listRendezVous.add(new ObjetRendezVous("Imène11","Dim, 23 avril 2017","12:00 - 16:00","No validé"));
       // listRendezVous.add(new ObjetRendezVous("Meriem87","Mar, 25 avril 2017","9:00 - 12:00","No validé"));
       // listRendezVous.add(new ObjetRendezVous("Arhab99","Sam, 26 avril 2017","9:00 - 12:00","No validé"));
        RequestQueue queue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                listRendezVous=new ArrayList<ObjetRendezVous>();
                Gson gson=new Gson();
                listRendezVous= Arrays.asList(gson.fromJson(jsonArray.toString(), ObjetRendezVous[].class));
                recyclerView.setAdapter(new RendezVousAdapter(listRendezVous));
               // Toast.makeText(ListRendezVous.this,listRendezVous.get(1).getUserName(),Toast.LENGTH_SHORT).show();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(ListRendezVous.this,"Erreur",Toast.LENGTH_SHORT).show();

            }
        });
        queue.add(jsonArrayRequest);
      /*  try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        /*try {
            JSONArray response = future.get();
            Gson gson=new Gson();
            listRendezVous= Arrays.asList(gson.fromJson(response.toString(), ObjetRendezVous[].class));
            Toast.makeText(ListRendezVous.this,listRendezVous.get(1).getUserName(),Toast.LENGTH_SHORT).show();
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        }*/

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
