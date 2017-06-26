package com.example.nesrine.projetmobile;

/**
 * Created by Nesrine on 23/04/2017.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

/**
 * Created by Imène on 22/04/2017.
 */

public class LogementAdapter extends RecyclerView.Adapter<LogementViewHolder>  //implements View.OnClickListener
{
    List<Logement> list;
    private OnItemClickListener mOnItemClickListener;
   // private final View.OnClickListener mOnClickListener = new MyOnClickListener();


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public LogementAdapter(List<Logement> list,OnItemClickListener onItemClickListener) {
        this.list = list;
        mOnItemClickListener=onItemClickListener;

    }

    //créer les viewHolder
    @Override
    public LogementViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_annonce,viewGroup,false);
        //view.setOnClickListener(mOnClickListener);
        return new LogementViewHolder(view);
    }

    // remplir les cellules avec le texte/image de chaque Logement
    @Override
    public void onBindViewHolder(LogementViewHolder holder, final int position) {
        Logement myLogement = list.get(position);
        holder.bind(myLogement);
        holder.container.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void remove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void add(Logement logement, int position) {
        list.add(position, logement);
        notifyItemInserted(position);
    }
}
