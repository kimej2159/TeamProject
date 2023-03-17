package com.example.eunji_timer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


public class No2_1Activity extends AppCompatActivity {
    LinearLayout timeCountSettingLV, timeCountLV;
    EditText hourET, minuteET, secondET;
    TextView hourTV, minuteTV, secondTV, finishTV;
    Button startBtn, stopBtn;
    int hour, minute, second;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no2_1_timer);

        Button dudug = findViewById(R.id.dudug);
        dudug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent4);
            }
        });




            setContentView(R.layout.activity_no2_1_timer);

            timeCountSettingLV = (LinearLayout)findViewById(R.id.timeCountSettingLV);
            timeCountLV = (LinearLayout)findViewById(R.id.timeCountLV);

            hourET = (EditText)findViewById(R.id.hourET);
            minuteET = (EditText)findViewById(R.id.minuteET);
            secondET = (EditText)findViewById(R.id.secondET);

            hourTV = (TextView)findViewById(R.id.hourTV);
            minuteTV = (TextView)findViewById(R.id.minuteTV);
            secondTV = (TextView)findViewById(R.id.secondTV);
            finishTV = (TextView)findViewById(R.id.finishTV);

            startBtn = (Button)findViewById(R.id.startBtn);
            stopBtn = findViewById(R.id.stopBtn);

            //정지버튼 이벤트 처리
            stopBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onStop();
                }
            });

            // 시작버튼 이벤트 1처리
            startBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    timeCountSettingLV.setVisibility(View.GONE);
                    timeCountLV.setVisibility(View.VISIBLE);

                    hourTV.setText(hourET.getText().toString());
                    minuteTV.setText(minuteET.getText().toString());
                    secondTV.setText(secondET.getText().toString());

                    if(hourET.getText().toString().equals("")){
                        hourET.setText("0");
                    }
                    if(minuteET.getText().toString().equals("")){
                        minuteET.setText("0");
                    }
                    if(secondET.getText().toString().equals("")){
                        secondET.setText("0");
                    }

                    hour = Integer.parseInt(hourET.getText().toString());
                    minute = Integer.parseInt(minuteET.getText().toString());
                    second = Integer.parseInt(secondET.getText().toString());

                    Timer timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            // 반복실행할 구문

                            // 0초 이상이면
                            if(second != 0) {
                                //1초씩 감소
                                second--;

                                // 0분 이상이면
                            } else if(minute != 0) {
                                // 1분 = 60초
                                second = 60;
                                second--;
                                minute--;

                                // 0시간 이상이면
                            } else if(hour != 0) {
                                // 1시간 = 60분
                                second = 60;
                                minute = 60;
                                second--;
                                minute--;
                                hour--;
                            }

                            //시, 분, 초가 10이하(한자리수) 라면
                            // 숫자 앞에 0을 붙인다 ( 8 -> 08 )
                            if(second <= 9){
                                secondTV.setText("0" + second);
                            } else {
                                secondTV.setText(Integer.toString(second));
                            }

                            if(minute <= 9){
                                minuteTV.setText("0" + minute);
                            } else {
                                minuteTV.setText(Integer.toString(minute));
                            }

                            if(hour <= 9){
                                hourTV.setText("0" + hour);
                            } else {
                                hourTV.setText(Integer.toString(hour));
                            }

                            // 시분초가 다 0이라면 toast를 띄우고 타이머를 종료한다..
                            if(hour == 0 && minute == 0 && second == 0) {
                                timer.cancel();//타이머 종료
                                finishTV.setText("타이머가 종료되었습니다.");
                            }
                        }
                    };

                    //타이머를 실행
                    timer.schedule(timerTask, 0, 1000); //Timer 실행


                }

            });

        }


    }


