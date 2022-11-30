package com.adsi5226.popayanturims;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;




public class DrawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;


    @Override
    public void setContentView(View view) {

        drawerLayout= (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base,null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContaoner);
        container.addView(view);

        super.setContentView(drawerLayout);


        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbasr);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.nav_lugares:
                startActivity(new Intent(this,LugarsActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_Users:
                startActivity(new Intent(this,UsersActivity.class));
                overridePendingTransition(0,0);
                break;
//            case R.id.nav_tiposer:
//                startActivity(new Intent(this,TipoServicioActivity.class));
//                overridePendingTransition(0,0);
//                break;
            case R.id.nav_posts:
                startActivity(new Intent(this,PostActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_eventos:
                startActivity(new Intent(this,EventoActivity.class));
                overridePendingTransition(0,0);
                break;


        }

        return false;
    }

    protected  void allocateActivityTitle(String titllestring){
        if (getSupportActionBar()!= null){
            getSupportActionBar().setTitle(titllestring);
        }
    }
}