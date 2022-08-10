package com.seu.kfmcapp;

import  retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitPost {

public static Retrofit retrofit;

public static Retrofit getRetrofitInstance(){
    if (retrofit == null){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.9:1107/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    return retrofit;
}
}
