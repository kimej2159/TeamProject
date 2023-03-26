package com.example.eunji_timer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.ListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    ListFragment list_fragment;

    //activity_no1_sportslist에서 프래그먼트 붙이기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNew = findViewById(R.id.btnNew);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), No1Activity.class);

                startActivity(intent);

            }
        });

    }

    public void onChangeFrag(int fragNum) {

        if (fragNum == 1) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contain, list_fragment).commit();
            // SubFragment에서 SubFragment2로 대체
        }

    }
}
