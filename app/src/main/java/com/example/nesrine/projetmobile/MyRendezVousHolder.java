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

public class MyRendezVousHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView userName;
    private TextView date;
    private TextView time;
    private TextView status;
    private ImageView btn_accept;
    private ImageView   btn_reject;




    public MyRendezVousHolder(View itemView) {
        super(itemView);
        userName = (TextView) itemView.findViewById(R.id.text_username);
        date= (TextView) itemView.findViewById(R.id.text_date);
        time= (TextView) itemView.findViewById(R.id.text_heure);
        status= (TextView) itemView.findViewById(R.id.text_status);
        btn_accept=(ImageView) itemView.findViewById(R.id.accept) ;
        btn_reject=(ImageView) itemView.findViewById(R.id.refuse) ;

        btn_accept.setOnClickListener(this);
        btn_reject.setOnClickListener(this);
      //  btn_reject.setOnClickListener(this);



    }
    public void bind(ObjetRendezVous myObject){
        userName.setText(myObject.getUserName());
        date.setText(myObject.getDate());
        time.setText(myObject.getHeure());
        status.setText(myObject.getStatus());

    }
    @Override
    public void onClick(View v) {

        if (v.getId() == btn_accept.getId()){
            status.setText("Validé");
        }
        if (v.getId() == btn_reject.getId()){
            status.setText("Rejecté");
        }



    }
}
