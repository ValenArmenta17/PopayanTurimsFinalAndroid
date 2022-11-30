package com.adsi5226.popayanturims;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adsi5226.popayanturims.Adapter.Adapter;
import com.adsi5226.popayanturims.Adapter.AdapterEvento;
import com.adsi5226.popayanturims.Modelo.Evento;
import com.adsi5226.popayanturims.Retrofit_Data.RetrofitApiService;
import com.adsi5226.popayanturims.Retrofit_Data.RetrofitClient;
import com.adsi5226.popayanturims.databinding.ActivityEventoBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventoActivity extends DrawerBaseActivity  implements Adapter.OnQueryTextListener {

    private RecyclerView recyclerViewevent;
    AdapterEvento adapterEvento;
    private ArrayList<Evento> evento;
    private RetrofitApiService retrofitApiService;
    private RetrofitClient retrofitClient;
    private SearchView svSearch;
    ActivityEventoBinding activityEventoBindingBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEventoBindingBinding = ActivityEventoBinding.inflate(getLayoutInflater());
        setContentView(activityEventoBindingBinding.getRoot());

        allocateActivityTitle("Eventos");
        recyclerViewevent = findViewById(R.id.recyclerViewEvent);
        evento= new ArrayList<>();
        svSearch = findViewById(R.id.svSearch);
        retrofitApiService= RetrofitClient.getApiService();
        mostrarNoticias();
    }
    private void mostrarNoticias() {

        Call<List<Evento>> call= retrofitApiService.getEvento();
        call.enqueue(new Callback<List<Evento>>() {


            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if (!response.isSuccessful()){

                    Toast.makeText(EventoActivity.this, "Error codigo" +response.code(),Toast.LENGTH_SHORT).show();
                }

                List<Evento> Event= response.body();
                recyclerViewevent.setLayoutManager(new LinearLayoutManager(EventoActivity.this));
                adapterEvento= new AdapterEvento((Context) EventoActivity.this, (ArrayList<Evento>)Event);
                recyclerViewevent.setAdapter(adapterEvento);

            }



            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {

                Toast.makeText(EventoActivity.this, "Fallo en conexion" +t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }


}
