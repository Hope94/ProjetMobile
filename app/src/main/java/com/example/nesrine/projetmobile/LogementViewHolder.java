package com.example.nesrine.projetmobile;

/**
 * Created by Nesrine on 23/04/2017.
 */


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Imène on 22/04/2017.
 */

public class LogementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView tViewRegion;
    private TextView tViewPrix;
    private TextView tViewType;
    private ImageView imView;

    public LogementViewHolder(View itemView) {
        super(itemView);

        tViewRegion= (TextView) itemView.findViewById(R.id.textViewNom);
        tViewPrix = (TextView) itemView.findViewById(R.id.textViewPrix);
        tViewType= (TextView) itemView.findViewById(R.id.textViewType);
        imView = (ImageView) itemView.findViewById(R.id.imageView2);
        itemView.setOnClickListener(this);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un Logement
    public void bind(Logement myLogement){
        tViewRegion.setText(myLogement.getRegion());
        tViewPrix.setText(String.valueOf(myLogement.getPrix()));
        tViewType.setText(myLogement.getType());
        imView.setImageResource(myLogement.getImage());
    }
    @Override
    public void onClick(View v) {
        Context context=v.getContext();
        Intent intent=new Intent(context,DetailLog.class);
        context.startActivity(intent);


    }
}
