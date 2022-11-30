package com.adsi5226.popayanturims;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adsi5226.popayanturims.Adapter.Adapter;
import com.adsi5226.popayanturims.Adapter.AdapterPosts;
import com.adsi5226.popayanturims.Modelo.Posts;
import com.adsi5226.popayanturims.Retrofit_Data.RetrofitApiService;
import com.adsi5226.popayanturims.Retrofit_Data.RetrofitClient;
import com.adsi5226.popayanturims.databinding.ActivityPostBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends DrawerBaseActivity  implements Adapter.OnQueryTextListener  {

    private RecyclerView     recyclerViewpost;

    AdapterPosts adapterPosts;
    private ArrayList<Posts> posts;
    private RetrofitApiService retrofitApiService;
    private RetrofitClient retrofitClient;
    private SearchView svSearch;
    ActivityPostBinding activityPostBinding;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPostBinding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(activityPostBinding.getRoot());
        allocateActivityTitle("post");

        recyclerViewpost = findViewById(R.id.recyclerViewPost);
        posts= new ArrayList<>();
        svSearch = findViewById(R.id.svSearch);
        retrofitApiService= RetrofitClient.getApiService();
        mostrarNoticias();
    }
    private void mostrarNoticias() {

        Call<List<Posts>> call= retrofitApiService.getPost();
        call.enqueue(new Callback<List<Posts>>() {


            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (!response.isSuccessful()){

                    Toast.makeText(PostActivity.this, "Error codigo" +response.code(),Toast.LENGTH_SHORT).show();
                }

                List<Posts> posts= response.body();
                recyclerViewpost.setLayoutManager(new LinearLayoutManager(PostActivity.this));
                adapterPosts= new AdapterPosts((Context) PostActivity.this, (ArrayList<Posts>)posts);
                recyclerViewpost.setAdapter(adapterPosts);

            }



            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

                Toast.makeText(PostActivity.this, "Fallo en conexion" +t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }


}
