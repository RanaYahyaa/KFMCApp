package com.seu.kfmcapp;

import  retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitPost {

public static Retrofit retrofit;

public static Retrofit getRetrofitInstance(){
    if (retrofit == null){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:1107/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    return retrofit;
}
}
