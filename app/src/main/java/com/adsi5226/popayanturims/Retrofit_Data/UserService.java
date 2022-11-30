package com.adsi5226.popayanturims.Retrofit_Data;

import com.adsi5226.popayanturims.Modelo.RegisterRequest;
import com.adsi5226.popayanturims.Modelo.RegisterResponse;
import com.adsi5226.popayanturims.Modelo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {



    @POST("user")
    Call<RegisterResponse>registerUsers(@Body RegisterRequest registerRequest);


    @GET("user")
    static Call<List<User>> getUser() {
        return null;
    }

    @POST("eliminar/{id}")
    Call<User>deletePersona(@Path("id")int id);
}
