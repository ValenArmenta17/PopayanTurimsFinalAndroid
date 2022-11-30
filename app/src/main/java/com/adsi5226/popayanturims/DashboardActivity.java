package com.adsi5226.popayanturims;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.adsi5226.popayanturims.databinding.ActivityDashboardBinding;

public class DashboardActivity extends DrawerBaseActivity {
ActivityDashboardBinding activityDashboardBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        allocateActivityTitle("Dashboar");
        setContentView(activityDashboardBinding.getRoot());
    }


    public void Lugares(View view){
        Intent intent = new Intent(DashboardActivity.this,LugarsActivity.class);
        startActivity(intent);
    }
    public void post(View view){
        Intent intent = new Intent(DashboardActivity.this,PostActivity.class);
        startActivity(intent);
    }
    public void Eventos(View view){
        Intent intent = new Intent(DashboardActivity.this,EventoActivity.class);
        startActivity(intent);
    }
    public void atras(View view){
        Intent intent = new Intent(DashboardActivity.this,SingInActivity.class);
        startActivity(intent);

    }
    public void Logout(View view){
        Intent intent = new Intent(DashboardActivity.this,SingInActivity.class);
        startActivity(intent);
        Toast.makeText(DashboardActivity.this, "Finalizo su seccion", Toast.LENGTH_SHORT).show();
    }
}