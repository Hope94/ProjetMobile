package com.example.nesrine.projetmobile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nesrine on 22/04/2017.
 */

public class RendezVousAdapter extends  RecyclerView.Adapter<MyRendezVousHolder>{
    public List<ObjetRendezVous> list;
    private String url="http://192.168.43.71:8080/postStatus";
    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public RendezVousAdapter(List<ObjetRendezVous> list,OnItemClickListener onItemClickListener) {
        this.list = list;
        mOnItemClickListener=onItemClickListener;
    }

    @Override
    public MyRendezVousHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_rendezvous,viewGroup,false);
        return new MyRendezVousHolder(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(final MyRendezVousHolder myViewHolder, final int position) {
        final ObjetRendezVous myObject = list.get(position);
        myViewHolder.bind(myObject);
        myViewHolder.container.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });

        myViewHolder.btn_accept.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                TextView status=(TextView)myViewHolder.container.findViewById(R.id.text_status);
                status.setText("Valide   ");
                RequestQueue queue= Volley.newRequestQueue(myViewHolder.container.getContext());
                StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(myViewHolder.container.getContext(),"Rendez-vous validé",Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(myViewHolder.container.getContext(),volleyError.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map = new HashMap<String, String>();
                        map.put("id_rdv",myObject.getId_rdv());
                        map.put("status","Valide   ");
                        return map;
                    }
                };

                queue.add(request);

            }
        });

        myViewHolder.btn_reject.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView status=(TextView)myViewHolder.container.findViewById(R.id.text_status);
                status.setText("Invalide");
                RequestQueue queue= Volley.newRequestQueue(myViewHolder.container.getContext());
                StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(myViewHolder.container.getContext(),"Rendez-vous non validé",Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(myViewHolder.container.getContext(),volleyError.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map = new HashMap<String, String>();
                        map.put("id_rdv",myObject.getId_rdv());
                        map.put("status","Invalide");
                        return map;
                    }
                };

                queue.add(request);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}
