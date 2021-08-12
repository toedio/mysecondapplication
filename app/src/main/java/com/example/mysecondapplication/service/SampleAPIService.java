package com.example.mysecondapplication.service;

import com.example.mysecondapplication.repository.Person;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SampleAPIService {

    @POST(".")
    Call<Object> sendData(@Body Person person);
}
