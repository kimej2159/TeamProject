package com.example.eunji_timer;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.eunji_timer.Common.CommonMethod;

public class No1_1_Activity extends AppCompatActivity {
    private static final String TAG = "No1_1_Activity";

    EditText titleBtn, timeBtn;
    Button saveBtn, cancleBtn;

    // 위험권한 : 실행시 허용여부를 다시 물어봄
    private void checkDangerousPermissions() {
        String[] permissions = {
                // 위험권한 내용 : 메니페스트에 권한을 여기에 적음
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.ACCESS_MEDIA_LOCATION,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_no1_1_makelist);

        checkDangerousPermissions();

        titleBtn = findViewById(R.id.titleBtn);
        timeBtn = findViewById(R.id.timeBtn);
        saveBtn = findViewById(R.id.saveBtn);
        cancleBtn = findViewById(R.id.cancleBtn);


        //타이틀
        titleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleBtn.getText().toString();
                String time = timeBtn.getText().toString();

                CommonMethod commonMethod = new CommonMethod();
                //데이터를 파라미터로 보낸다 : 서버에서 받을 때 제목, 시간으로 받는다
                commonMethod.setParams("title", title);
                commonMethod.setParams("time", time);
            }
        });

    }
}
