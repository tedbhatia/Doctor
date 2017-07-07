package com.example.doctor.support.service;


import com.example.doctor.ui.model.Appointments;
import com.example.doctor.ui.model.Body_Parts;
import com.example.doctor.ui.model.Doctor;
import com.example.doctor.ui.model.Insurance;
import com.example.doctor.ui.model.Measurement_Info;
import com.example.doctor.ui.model.MedicineModel;
import com.example.doctor.ui.model.ProcedureModel;
import com.example.doctor.ui.model.SymptomModel;
import com.example.doctor.ui.model.find_doctor_model;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.example.doctor.ui.activity.MyAppointments.idDoc;
import static com.facebook.HttpMethod.GET;

/**
 * Created by Aviral on 21-06-2017.
 */

public interface RequestInterface {

    @GET("api/doctor_list")
    Call<List<find_doctor_model>> getJSON();

    @GET("api/medicine_list")
    Call<List<MedicineModel>> getJSONmed();

    @GET("api/procedure_list")
    Call<List<ProcedureModel>> getJSONproc();

    @GET("api/appointment_list")
    Call<List<Appointments>> getAppointment();



    @GET("api/insurance_list")
    Call<List<Insurance>> getJSONinsurance();

    @GET("api/measurement_list")
    Call<List<Measurement_Info>> getJSONmeas();

    @GET("api/bodypart_list")
    Call<List<Body_Parts>> getBODY();

    @GET("api/symptom_list")
    Call<List<SymptomModel>> getSymptoms();

    @FormUrlEncoded
    @POST("api/login/")
    Call<ResponseBody> login(@Field("username") String username, @Field("password") String password);

    @GET("api/logout/")
    Call<ResponseBody> logout();

    @FormUrlEncoded
    @POST("api/register/")
    Call<ResponseBody> signup(@Field("username") String username, @Field("password") String password, @Field("email") String email);

    @GET("api/doctor_list")
    Call<List<Doctor>> getMYDoctor();
}
