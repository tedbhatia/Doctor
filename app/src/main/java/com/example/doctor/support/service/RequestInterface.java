package com.example.doctor.support.service;


import com.example.doctor.ui.model.Insurance;
import com.example.doctor.ui.model.MedicineModel;
import com.example.doctor.ui.model.ProcedureModel;
import com.example.doctor.ui.model.find_doctor_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.facebook.HttpMethod.GET;

/**
 * Created by Aviral on 21-06-2017.
 */

public interface RequestInterface {

    @GET("doctors_api/?format=json")
    Call<List<find_doctor_model>> getJSON();

    @GET("medicines_api/?format=json")
    Call<List<MedicineModel>> getJSONmed();

    @GET("procedures_api/?format=json")
    Call<List<ProcedureModel>> getJSONproc();

    @GET("insurances_api/?format=json")
    Call<List<Insurance>> getJSONinsurance();
}
