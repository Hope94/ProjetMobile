package com.example.nesrine.projetmobile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Nesrine on 22/04/2017.
 */

public class RendezVousAdapter extends  RecyclerView.Adapter<MyRendezVousHolder>{
    public List<ObjetRendezVous> list;
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
    public void onBindViewHolder(MyRendezVousHolder myViewHolder, final int position) {
        final ObjetRendezVous myObject = list.get(position);
        myViewHolder.container.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });

        myViewHolder.btn_accept.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Accept CLicked",Toast.LENGTH_SHORT).show();
            }
        });

        myViewHolder.btn_reject.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Reject CLicked",Toast.LENGTH_SHORT).show();
            }
        });


        myViewHolder.bind(myObject);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}
