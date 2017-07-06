package com.example.aviral.retrofacttrial;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Aviral on 21-06-2017.
 */

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("doctors_api/?format=json")
    Call<List<AndroidVersion>> getJSON();
}
