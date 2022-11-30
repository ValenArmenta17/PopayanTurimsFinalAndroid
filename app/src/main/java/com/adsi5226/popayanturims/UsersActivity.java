package com.adsi5226.popayanturims;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.adsi5226.popayanturims.Adapter.UserAdapter;

import com.adsi5226.popayanturims.Modelo.User;
import com.adsi5226.popayanturims.Retrofit_Data.RetrofitApiService;
import com.adsi5226.popayanturims.Retrofit_Data.RetrofitClient;

import com.adsi5226.popayanturims.Retrofit_Data.UserService;
import com.adsi5226.popayanturims.databinding.ActivityUsersBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends DrawerBaseActivity {

    ActivityUsersBinding activityUsersBinding;
    private RecyclerView recyclerViewuser;

    UserAdapter adapterUser;
    UserService service;
    private ArrayList<User> users;
    private RetrofitApiService retrofitApiService;
    private RetrofitClient retrofitClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityUsersBinding = ActivityUsersBinding.inflate(getLayoutInflater());
        setContentView(activityUsersBinding.getRoot());
        allocateActivityTitle("Users");

        recyclerViewuser = findViewById(R.id.recyclerViewUser);
        users = new ArrayList<>();
        retrofitApiService = RetrofitClient.getApiService();
        mostrarNoticias();
    }

    private void mostrarNoticias() {

        Call<List<User>> call = retrofitApiService.getUser();
        call.enqueue(new Callback<List<User>>() {


            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {

                    Toast.makeText(UsersActivity.this, "Error codigo" + response.code(), Toast.LENGTH_SHORT).show();
                }

                List<User> users = response.body();
                recyclerViewuser.setLayoutManager(new LinearLayoutManager(UsersActivity.this));
                adapterUser = new UserAdapter((Context) UsersActivity.this, (ArrayList<User>) users);
                recyclerViewuser.setAdapter(adapterUser);

            }


            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                Toast.makeText(UsersActivity.this, "Fallo en conexion" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void NuevoUser(View view) {
        Intent intent = new Intent(UsersActivity.this, ReActivity.class);
        startActivity(intent);
        Toast.makeText(UsersActivity.this, "Registrar usuario", Toast.LENGTH_SHORT).show();
    }

    public void deleteUser(int id) {
        service = (UserService) UserService.getUser();
        Call<User> call = service.deletePersona(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UsersActivity.this, "Se Elimino el registro", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Error:", t.getMessage());
            }
        });
        Intent intent = new Intent(UsersActivity.this, MainActivity.class);
        startActivity(intent);
    }

}