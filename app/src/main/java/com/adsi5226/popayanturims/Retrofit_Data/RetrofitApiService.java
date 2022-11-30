package com.adsi5226.popayanturims.Retrofit_Data;


import com.adsi5226.popayanturims.Modelo.Evento;
import com.adsi5226.popayanturims.Modelo.Lugars;
import com.adsi5226.popayanturims.Modelo.Posts;
import com.adsi5226.popayanturims.Modelo.RegisterRequest;
import com.adsi5226.popayanturims.Modelo.RegisterResponse;
import com.adsi5226.popayanturims.Modelo.TipoServicio;
import com.adsi5226.popayanturims.Modelo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitApiService {


    @GET("tiposervicio")
    Call<List<TipoServicio>> getTiposervicios();


    @GET("lugar")
    Call<List<Lugars>> getLugars();


    @GET("post")
    Call<List<Posts>> getPost();

    @GET("Evento")
    Call<List<Evento>> getEvento();


    @GET("user")
    Call<List<User>> getUser();




}
