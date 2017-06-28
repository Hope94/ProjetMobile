package com.example.nesrine.projetmobile;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserType extends AppCompatActivity {
    private User user;
    private SharedPref shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);
        shared=new SharedPref(this,"owner");

    }
    public void onClickProp(View view){
        if(shared.isConnected()){
            Intent i = new Intent(this, ListRendezVous.class);
            i.putExtra("user",shared.getUserConnected());
            startActivity(i);

        }else{
            login();
        }


    }
    public void onClickClient(View view){
        Intent intent=new Intent(UserType.this,AnnoncesActivity.class);
        startActivity(intent);

    }
    public void login(){
        Intent i = new Intent(this, Login.class);
        startActivityForResult(i, 1);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                user=(User) data.getSerializableExtra("user");
                shared.saveConnectUser(user.getEmail());
            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }
}
