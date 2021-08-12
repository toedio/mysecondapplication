package com.example.mysecondapplication.config;

import com.example.mysecondapplication.service.SampleAPIService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitializer {

    Retrofit retrofit;

    public RetrofitInitializer(String baseUrl) {
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .client(client()).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public SampleAPIService sampleAPIService() {
        return retrofit.create(SampleAPIService.class);
    }

    private OkHttpClient client() {
        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
    }


}
