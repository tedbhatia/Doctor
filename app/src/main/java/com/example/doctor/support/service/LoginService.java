package com.example.doctor.support.service;

import com.example.doctor.ui.model.User;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Aviral on 26-06-2017.
 */

public interface LoginService {
    @POST("/admin/login/?next=/admin/")
    Call<User> basicLogin();
}

