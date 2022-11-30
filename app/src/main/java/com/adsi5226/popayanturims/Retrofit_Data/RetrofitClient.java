package com.adsi5226.popayanturims.Retrofit_Data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    private static Retrofit retrofit = null;

    public  static  final String URL_BASE ="http://10.0.2.2:8000/v1/";
    public static RetrofitApiService getApiService(){

        if (retrofit==null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit.create(RetrofitApiService.class);
    }

}
