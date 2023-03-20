package com.example.myteamproject_chanmin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;


public class WelcomePage extends Fragment {

    MainActivity mActivity;

    ImageView centerFind , teacherFind  , timer , comunity ;

    //프래그먼트 변수선언
    CenterFind centerFindFrag;
    TeacherFind teacherFindFrag;
    com.example.myteamproject_chanmin.Timer timerFrag;
    Comunity comunityFrag;


    //슬라이드 이미지 변수 초기화
    private ViewPager2 sliderViewPager;
    private LinearLayout layoutIndicator;
    private int[] images = new int[]{
            R.drawable.happy_birthday,
            R.drawable.happy_birthday,
            R.drawable.happy_birthday
    };
    private int currentPage = 0;
    Timer imgTimer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; //

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.welcomepage,
                container, false);





        mActivity = (MainActivity) getActivity();

        //레이아웃요소 초기화
        centerFind = viewGroup.findViewById(R.id.centerFind);
        teacherFind = viewGroup.findViewById(R.id.teacherFind);
        timer = viewGroup.findViewById(R.id.timer);
        comunity = viewGroup.findViewById(R.id.comunity);

        //프래그먼트 초기화
        centerFindFrag = new CenterFind();
        teacherFindFrag = new TeacherFind();
        timerFrag = new com.example.myteamproject_chanmin.Timer();
        comunityFrag = new Comunity();

        //슬라이더 뷰페이저 처리부분 초기화 및 메서드 호출
        sliderViewPager = viewGroup.findViewById(R.id.sliderViewPager);
        layoutIndicator = viewGroup.findViewById(R.id.layoutIndicators);
        sliderViewPager.setOffscreenPageLimit(1);
        sliderViewPager.setAdapter(new ImageSliderAdapter(mActivity.getApplicationContext(), images));

        sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });

        setupIndicators(images.length);

        imgTimer = new Timer();
        imgTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(images.length == currentPage) {
                    currentPage = 0;
                }
                sliderViewPager.setCurrentItem(currentPage++, true);
            }
        }, DELAY_MS, PERIOD_MS);


        //메인홈에서 아이콘 클릭시 화면 이동
        centerFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mActivity.onFragmentSelected("센터찾기" , centerFindFrag);
            }
        });
        teacherFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mActivity.onFragmentSelected("강사매칭" , teacherFindFrag);
            }
        });

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mActivity.onFragmentSelected("타이머" , timerFrag);
            }
        });

        comunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mActivity.onFragmentSelected("커뮤니티" , comunityFrag);
            }
        });


        return viewGroup;
    }//oncreate

    // 슬라이드 내 위치 표식 둥근 아이콘 설정
    private void setupIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(16, 8, 16, 8);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(mActivity.getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(mActivity.getApplicationContext(),
                    R.drawable.bg_indicator_inactive));
            indicators[i].setLayoutParams(params);
            layoutIndicator.addView(indicators[i]);
        }
        setCurrentIndicator(0);
    }


    // 선택 또는 보여지는 슬라이드 내 둥근 아이콘 색상 구분
    private void setCurrentIndicator(int position) {
        int childCount = layoutIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        mActivity.getApplicationContext(),
                        R.drawable.bg_indicator_active
                ));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        mActivity.getApplicationContext(),
                        R.drawable.bg_indicator_inactive
                ));
            }
        }
    }



}//Frag
