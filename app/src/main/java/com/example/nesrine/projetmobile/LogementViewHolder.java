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

import com.bumptech.glide.Glide;

/**
 * Created by Imène on 22/04/2017.
 */

public class LogementViewHolder extends RecyclerView.ViewHolder //implements View.OnClickListener
{
    private TextView tViewRegion;
    private TextView tViewPrix;
    private TextView tViewType;
    private ImageView imView;
    private Context context=null;
    //private String url1="http://www.eiffage-immobilier.fr/files/live/sites/eiffage-immo/files/contributed/visuels-programmes/appartement-neuf-nice-stella-rocca-diapo4.jpg";
    //private String url="http://af9e83bc.ngrok.io/";


    public LogementViewHolder(View itemView) {
        super(itemView);

        tViewRegion= (TextView) itemView.findViewById(R.id.textViewNom);
        tViewPrix = (TextView) itemView.findViewById(R.id.textViewPrix);
        tViewType= (TextView) itemView.findViewById(R.id.textViewType);
        imView = (ImageView) itemView.findViewById(R.id.imageView2);
        //itemView.setOnClickListener(this);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un Logement
    public void bind(Logement myLogement){
        tViewRegion.setText(myLogement.getRegion());
        tViewPrix.setText(Integer.toString((int) myLogement.getPrix())+" DA");
        tViewType.setText(myLogement.getType());
        context=imView.getContext();

        Glide.with(context).load(myLogement.getMainImage()).placeholder(R.drawable.ic_cloud).skipMemoryCache(true).into(imView);
        //imView.setImageResource(myLogement.getImage());
    }
  /*  @Override
    public void onClick(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, DetailLog.class);
        context.startActivity(intent);

    }*/

  /*  public void onBindViewHolder(LogementViewHolder holder, int position) {

        ImageView imageView = holder.imView;
        Glide.with(context)
                .load(url+"annonce/a1.jpg")
                .placeholder(R.drawable.ic_cloud)
                .into(imageView);
    }*/

}
