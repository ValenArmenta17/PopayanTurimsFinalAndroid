package com.adsi5226.popayanturims;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SingUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
    }
    public void onButtonir(View view){
        Intent intent = new Intent(SingUpActivity.this,DashboardActivity.class);
        startActivity(intent);
    }
}