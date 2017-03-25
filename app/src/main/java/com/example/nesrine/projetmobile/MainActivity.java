package com.example.nesrine.projetmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    // methode qui lance le détail de logement
    public void onClickLogement(View view){

        Intent intent=new Intent(MainActivity.this,DetailLogement.class);
        startActivity(intent);
    }
}
