package com.example.nesrine.projetmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);

    }
    public void onClickProp(View view){
        Intent intent=new Intent(UserType.this,ListRendezVous.class);
        startActivity(intent);
    }
    public void onClickClient(View view){
        Intent intent=new Intent(UserType.this,AnnoncesActivity.class);
        startActivity(intent);
    }
}
