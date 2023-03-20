package com.example.myteamproject_chanmin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.splashscreen.SplashScreen;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private static final String TAG = "main:MainActivity";



    Toolbar toolbar;
    WelcomePage welcomePage;
    CenterFind centerFind;
    TeacherFind teacherFind;
    ToolsFind toolsFind;
    Timer timer;
    Comunity comunity;
    LoginFrag loginFrag;
    JoinFrag joinFrag;

    ImageButton loginBtn;
    ImageButton joinBtn;


    FloatingActionButton fab;
    NavigationView nav_view;
    DrawerLayout draw_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);
        setContentView(R.layout.activity_main);


        // 프래그먼트 객체를 생성하고 프래임레이아웃에 초기화를 시킨다
        welcomePage = new WelcomePage();
        centerFind = new CenterFind();
        teacherFind = new TeacherFind();
        toolsFind = new ToolsFind();
        timer = new Timer();
        comunity = new Comunity();
        loginFrag = new LoginFrag();
        joinFrag = new JoinFrag();


        nav_view = findViewById(R.id.nav_view);
        // implement Listener 할때는 반드시 아래와 같이 정의한다
        nav_view.setNavigationItemSelectedListener(this);

        //HeaderView 에 접근 메소드
        View headerView = nav_view.getHeaderView(0);

        loginBtn = headerView.findViewById(R.id.loginBtn);
        joinBtn = headerView.findViewById(R.id.joinBtn);

        //headerView에 로그인버튼클릭시
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contain , loginFrag).commit();
                // 메뉴 선택후 드로어가 사라지게 한다
                draw_layout.closeDrawer(GravityCompat.START);
            }
        });
        //headerView에 회원가입버튼클릭시
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contain , joinFrag).commit();
                // 메뉴 선택후 드로어가 사라지게 한다
                draw_layout.closeDrawer(GravityCompat.START);
            }
        });

        // toolbar 적용 : res->theme->NoActionBar로 변경
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // drawerLayout을 찾아서 토클 리스너를 붙인다
        draw_layout = findViewById(R.id.draw_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, draw_layout, toolbar,
                R.string.draw_open, R.string.draw_close );
        draw_layout.addDrawerListener(toggle);
        toggle.syncState();


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contain, welcomePage).commit();
/*
        // 헤드 드로어에 접근해서 로그인 정보 표시하기
        int userLevel = 0;  // 0:관리자, 1:일반유저 -> DB에 있음
        //String loginID = "BTS";  // 관리자ID:BlackPink, 일반유저:BTS
        String loginID = "BlackPink";

        View headerView = nav_view.getHeaderView(0);
        // 헤드 드로어에 있는 TextView 2개
        TextView navLoginID = headerView.findViewById(R.id.loginID);
        navLoginID.setText("반갑습니다 " + loginID + "님");
        // 이미지뷰에 접근
        ImageView loginImage = headerView.findViewById(R.id.loginImage);
        Glide
                .with(this)  // -> .with(myfragment)
                //.load("https://image.jtbcplus.kr/data/contents/jam_photo/202212/19/ea593606-6855-4963-80b4-25323a0ec8d4.jpg")
                // .load(R.drawable.bts)
                .load(R.drawable.blackpink)
                .centerCrop()
                .circleCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .into(loginImage);
        //loginImage.setImageResource(R.drawable.bts);

        // userLevel이 0 이면 관리자라서 아래메뉴까지 보여주고
        // userLevel이 1 이면 일반유저라서 아래메뉴를 보여주지 않는다
        if(userLevel == 0){
            nav_view.getMenu().findItem(R.id.communi).setVisible(true);
        }else if(userLevel == 1){
            nav_view.getMenu().findItem(R.id.communi).setVisible(false);
        }

 */

        // 플로팅 버튼이 클릭되었을때
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own code",
                                Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item ) {
        // 클릭한 아이템의 id를 얻는다

        int id = item.getItemId();
        switch (id){
            case R.id.nav_home:
                onFragmentSelected("메인화면", welcomePage);
                break;
            case R.id.nav_centerfind:
                onFragmentSelected("센터찾기", centerFind);
                break;
            case R.id.nav_teacherfind:
                onFragmentSelected("강사찾기", teacherFind);
                break;
            case R.id.nav_toolfind:
                onFragmentSelected("운동용품", toolsFind);
                break;
            case R.id.nav_timer:
                onFragmentSelected("타이머", timer);
                break;
            case R.id.nav_cumunity:
                onFragmentSelected("커뮤니티", comunity);
                break;

        }
        // 메뉴 선택후 드로어가 사라지게 한다
        draw_layout.closeDrawer(GravityCompat.START);

        return true;
    }
    //                                  화면 순서,  내가 선택한 프래그먼트
    public void onFragmentSelected(String screen, Fragment selFragment){
        // 타이틀 이름 바꿈
        toolbar.setTitle(screen);
        // 선택한 프래그먼트 화면으로 교체
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contain, selFragment).commit();

    }

    // 뒤로가기를 눌렀을때 만약 드로어 창이 열려있으면 드로어 창을 닫고
    // 아니면 그냥 뒤로가기 원래 작업을 한다(여기서는 앱 종료)
    @Override
    public void onBackPressed() {
        if(draw_layout.isDrawerOpen(GravityCompat.START)){
            draw_layout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}