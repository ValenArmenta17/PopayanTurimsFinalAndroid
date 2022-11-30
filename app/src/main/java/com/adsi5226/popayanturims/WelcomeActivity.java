package com.adsi5226.popayanturims;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextView textView = findViewById(R.id.texwelcome);
        textView.animate().translationX(1000).setDuration(95).setStartDelay(2500);

    }



    public void onButtonSingUpClicked(View view){

        Intent intent = new Intent(WelcomeActivity.this,SingUpActivity.class);
        startActivity(intent);

    }
    public void onButtonSingInClicked(View view){
        Intent intent = new Intent(WelcomeActivity.this,ReActivity.class);
        startActivity(intent);


    }



}