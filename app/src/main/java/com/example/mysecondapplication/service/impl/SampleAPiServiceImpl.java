package com.example.mysecondapplication.service.impl;

import com.example.mysecondapplication.config.RetrofitInitializer;
import com.example.mysecondapplication.helper.SimpleCallback;
import com.example.mysecondapplication.repository.Person;
import com.example.mysecondapplication.service.SampleAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SampleAPiServiceImpl {

    SampleAPIService sampleAPIService = new RetrofitInitializer("https://hookb.in/pzzpYqRGEbhRPnrrPxMr/").sampleAPIService();

    public void sendData(Person person, SimpleCallback simpleCallback) {
        Call<Object> responseCall = sampleAPIService.sendData(person);

        responseCall.clone().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                simpleCallback.success = response.isSuccessful();
                simpleCallback.call();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                simpleCallback.success = false;
                simpleCallback.call();
            }
        });
    }
}
