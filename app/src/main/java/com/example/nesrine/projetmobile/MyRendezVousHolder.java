package com.example.nesrine.projetmobile;

import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nesrine on 22/04/2017.
 */

public class MyRendezVousHolder extends  RecyclerView.ViewHolder // implements View.OnClickListener
{
    private TextView userName;
    private TextView date;
    private TextView time;
    private TextView status;
    private TextView region;
    private TextView anno;
    public ImageView btn_accept;
    public ImageView btn_reject;
    public View container;





    public MyRendezVousHolder(View itemView) {
        super(itemView);
        userName = (TextView) itemView.findViewById(R.id.text_username);
        date= (TextView) itemView.findViewById(R.id.text_date);
        time= (TextView) itemView.findViewById(R.id.text_heure);
        status= (TextView) itemView.findViewById(R.id.text_status);
        anno=(TextView) itemView.findViewById(R.id.text_annonce);
        region=(TextView)itemView.findViewById(R.id.text_adr);
        btn_accept=(ImageView) itemView.findViewById(R.id.accept) ;
        btn_reject=(ImageView) itemView.findViewById(R.id.refuse) ;

      //  btn_accept.setOnClickListener(this);
        // btn_reject.setOnClickListener(this);
        container=itemView;
      //  btn_reject.setOnClickListener(this);



    }
    public void bind(ObjetRendezVous myObject){
        userName.setText(myObject.getUserName());
        date.setText(myObject.getDate());
        time.setText(myObject.getHeure());
        status.setText(myObject.getStatus());
        region.setText(myObject.getRegion());
        anno.setText(myObject.getNomAnn());

    }
   /* @Override
    public void onClick(View v) {

        if (v.getId() == btn_accept.getId()){
            status.setText("Valide   ");
        }
        if (v.getId() == btn_reject.getId()){
            status.setText("Invalide");
        }

    }*/




}
