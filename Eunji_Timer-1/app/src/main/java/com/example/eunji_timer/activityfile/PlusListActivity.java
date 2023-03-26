package com.example.eunji_timer.activityfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eunji_timer.R;

public class PlusListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pluslist);

        Button  listsave= findViewById(R.id.listsave);
        listsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), No1Activity.class);
                startActivity(intent3);
                finish();
            }
        });


        Button listcancel = findViewById(R.id.listcancel);
        listcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), No1Activity.class);
                startActivity(intent3);
                finish();
            }
        });
    }
}
