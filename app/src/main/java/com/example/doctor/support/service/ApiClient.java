package com.example.doctor.support.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aviral on 21-06-2017.
 */

public class ApiClient {

    private static final String BASE_URl = "http://med-4-u.appspot.com";
    private static Retrofit retrofit = null;

     public static Retrofit getClient(){
         if(retrofit==null){
             retrofit = new Retrofit.Builder()
                     .baseUrl(BASE_URl)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
         }
         return retrofit;
    }
}
