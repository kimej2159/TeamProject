package com.example.eunji_timer.activityfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eunji_timer.R;

public class No1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no1_sportslist);

        Button btnPlus = findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent plus = new Intent(v.getContext(), PlusListActivity.class);
                startActivity(plus);
                finish();
            }
        });




        Button btnReturn = findViewById(R.id.stretch);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), No2Activity.class);
                startActivity(intent2);
            }
        });

//        Button btnRun = findViewById(R.id.btnRun);
//        btnRun.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent5 = new Intent(getApplicationContext(), )
//            }
//        });
    }
}