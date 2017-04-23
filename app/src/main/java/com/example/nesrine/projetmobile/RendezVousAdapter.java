package com.example.nesrine.projetmobile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nesrine on 22/04/2017.
 */

public class RendezVousAdapter extends  RecyclerView.Adapter<MyRendezVousHolder>{
    List<ObjetRendezVous> list;

    public RendezVousAdapter(List<ObjetRendezVous> list) {
        this.list = list;

    }

    @Override
    public MyRendezVousHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_rendezvous,viewGroup,false);
        return new MyRendezVousHolder(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(MyRendezVousHolder myViewHolder, int position) {
        ObjetRendezVous myObject = list.get(position);
        myViewHolder.bind(myObject);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}
