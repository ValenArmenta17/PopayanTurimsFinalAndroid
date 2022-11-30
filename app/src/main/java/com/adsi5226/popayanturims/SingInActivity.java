package com.adsi5226.popayanturims;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SingInActivity extends AppCompatActivity {
    TextView  textViewregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);


    }

    public void GOO(View view){
        Intent intent = new Intent(SingInActivity.this,DashboardActivity.class);
        startActivity(intent);
    }
    public void textViewregister(View view){
        Intent intent = new Intent(SingInActivity.this,SingUpActivity.class);
        startActivity(intent);
    }
}