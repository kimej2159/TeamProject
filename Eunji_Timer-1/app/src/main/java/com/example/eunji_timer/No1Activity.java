package com.example.eunji_timer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.eunji_timer.Common.CommonMethod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class No1Activity extends AppCompatActivity {

    ListFragment list_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no1_sportslist);
/*
        CommonMethod commonMethod = new CommonMethod();
        commonMethod.getData("asjid", new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                response.body();


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });*/

        Button btnReturn = findViewById(R.id.stretch);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), No2Activity.class);

                startActivity(intent2);

            }
        });

        FragmentManager manager = getSupportFragmentManager();
        list_fragment = (ListFragment) manager
                .findFragmentById(R.id.listfragment);
    }


}