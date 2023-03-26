package com.example.myteamproject_chanmin.ASyncTask;


import static com.example.myteamproject_chanmin.Common.CommonMethod.ipConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    public Retrofit getApiClient(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipConfig) // 톰캣까지(서버 까지 접근 경로)
                .client( new OkHttpClient.Builder().connectTimeout(10 , TimeUnit.SECONDS).build())
                .addConverterFactory(ScalarsConverterFactory.create()) // json String사용
                .build();

        return retrofit;
    }



}
