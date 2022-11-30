package com.adsi5226.popayanturims;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adsi5226.popayanturims.Adapter.Adapter;
import com.adsi5226.popayanturims.Adapter.AdapterLugars;
import com.adsi5226.popayanturims.Modelo.Lugars;
import com.adsi5226.popayanturims.Retrofit_Data.RetrofitApiService;
import com.adsi5226.popayanturims.Retrofit_Data.RetrofitClient;
import com.adsi5226.popayanturims.databinding.ActivityLugarsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LugarsActivity extends DrawerBaseActivity  implements Adapter.OnQueryTextListener  {
    private RecyclerView recyclerViewl;

    AdapterLugars adapterLugars;
    private ArrayList<Lugars> lugars;
    private RetrofitApiService retrofitApiService;
    private RetrofitClient retrofitClient;
    private SearchView svSearch;
    ActivityLugarsBinding activityLugarsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLugarsBinding = ActivityLugarsBinding.inflate(getLayoutInflater());
        setContentView(activityLugarsBinding.getRoot());
        allocateActivityTitle("lugares");


        recyclerViewl = findViewById(R.id.recyclerViewlu);
         lugars= new ArrayList<>();
        svSearch = findViewById(R.id.svSearch);
        retrofitApiService= RetrofitClient.getApiService();
        mostrarNoticias();
    }
    private void mostrarNoticias() {

        Call<List<Lugars>> call= retrofitApiService.getLugars();
        call.enqueue(new Callback<List<Lugars>>() {


            @Override
            public void onResponse(Call<List<Lugars>> call, Response<List<Lugars>> response) {
                if (!response.isSuccessful()){

                    Toast.makeText(LugarsActivity.this, "Error codigo" +response.code(),Toast.LENGTH_SHORT).show();
                }

                List<Lugars> lugars= response.body();
                recyclerViewl.setLayoutManager(new LinearLayoutManager(LugarsActivity.this));
                adapterLugars= new AdapterLugars((Context) LugarsActivity.this, (ArrayList<Lugars>)lugars);
                recyclerViewl.setAdapter(adapterLugars);

            }



            @Override
            public void onFailure(Call<List<Lugars>> call, Throwable t) {

                Toast.makeText(LugarsActivity.this, "Fallo en conexion" +t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }


}
