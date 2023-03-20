package com.example.my17_fragment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    MainFragment mainFragment;
    SubFragment subFragment;
    SubFragment2 subFragment2;
    PlusFragment plusFragment;
    SubFragment1_1 subFragment1_1;
    SubFragment1_2 subFragment1_2;
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 반드시 androidx. 으로 임포트
        actionBar = getSupportActionBar();
        // 액션바를 숨기는 2가지 방법
        // 1. res->values->themes.xml => parent:NoActionBar로 수정
        // 2. 동적으로 : actionBar.hide();

        // 액션바의 타이틀로고 바꾸기
        actionBar.setLogo(R.drawable.back); // 뒤로가기
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);

        // 타이틀로고에 클릭이벤트 만들기 : 디폴트 true
        // 그리고 onOptionsItemSelected() 에 추가
        actionBar.setDisplayHomeAsUpEnabled(true);


        // 이미 xml에 id를 주고 선언한 경우
        /*mainFragment = (MainFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mainFragment);*/
        mainFragment = new MainFragment();
        subFragment = new SubFragment();
        subFragment2 = new SubFragment2();
        subFragment1_1 = new SubFragment1_1();
        subFragment1_2 = new SubFragment1_2();

        // 초기 메인화면에 프래그먼트 초기화 시키기 : 동적으로
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contain, mainFragment).commit();



        //---------------------------------------------------------


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);

        return true;
    }


    @Override                                 //내가 선택한 옵션메뉴
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 그 메뉴 아이템에서 내가 부여한 아이디를 가져올수 있다
        int curId = item.getItemId();

        switch (curId){

            case R.id.menu:
                Toast.makeText(this,
                        "메뉴 버튼이 클릭됨!", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:

                Toast.makeText(this,
                        "뒤로가기 버튼이 클릭됨!", Toast.LENGTH_SHORT).show();
                //finish(); // 앱 종료

                onBackPressed();


                break;
        }

        return true;
    }

    // 프래그먼트에서 화면 전환을 요청받는 매소드
    // MainFragment:1,  SubFragment:2
    public void onChangeFrag(int fragNum) {
        // MainFragment에서 눌렸으니 SubFragment로 대체
        if (fragNum == 1) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contain, subFragment).commit();
            // SubFragment에서 SubFragment2로 대체
        } else if (fragNum == 2) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contain, subFragment2).commit();

            // SubFragment에서 눌렸으니 MainFragment로 대체
        } else if (fragNum == 3) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contain, mainFragment).commit();

        } else if (fragNum == 4) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contain, subFragment1_1 ).commit();

        } else if (fragNum == 5) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contain, subFragment1_2 ).commit();
        }
    }


}

