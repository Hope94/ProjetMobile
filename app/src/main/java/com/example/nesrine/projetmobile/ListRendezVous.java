package com.example.nesrine.projetmobile;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ListRendezVous extends AppCompatActivity {
    private RecyclerView recyclerView;
    private  String url="http://192.168.43.71:8080/getRendezVous";
    private  String url_status="http://192.168.43.71:8080/postStatus";
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
        RequestQueue queue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                listRendezVous=new ArrayList<ObjetRendezVous>();
                Gson gson=new Gson();
                listRendezVous= Arrays.asList(gson.fromJson(jsonArray.toString(), ObjetRendezVous[].class));
                recyclerView.setAdapter(new RendezVousAdapter(listRendezVous,new RendezVousAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, final int position) {

                        ///list item was clicked
                       Toast.makeText(ListRendezVous.this,"Item CLicked",Toast.LENGTH_SHORT).show();
                    /*    FirebaseJobDispatcher dispatcher=new FirebaseJobDispatcher(new GooglePlayDriver(view.getContext()));
                        Job myJob=dispatcher.newJobBuilder()
                                .setService(MyJobService.class)
                                .setTrigger(Trigger.executionWindow(0,10))
                                .setTag("my-unique-tag")
                                .setConstraints(Constraint.ON_ANY_NETWORK)
                                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                                .build();
                        dispatcher.mustSchedule(myJob);*/

                       /* RequestQueue queue= Volley.newRequestQueue(ListRendezVous.this);
                        StringRequest request=new StringRequest(Request.Method.POST, url_status, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                Toast.makeText(ListRendezVous.this,s,Toast.LENGTH_SHORT).show();

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(ListRendezVous.this,volleyError.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                            }
                        })
                        {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> map = new HashMap<String, String>();
                                map.put("id_rdv",listRendezVous.get(position).getId_rdv());
                                map.put("status","Valide");
                                return map;
                            }
                        };

                        queue.add(request); */

                    }
                }));
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

    public void validate(int i){

    }

}
