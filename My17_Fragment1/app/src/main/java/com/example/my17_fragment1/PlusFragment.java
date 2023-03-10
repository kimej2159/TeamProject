package com.example.my17_fragment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class PlusFragment extends Fragment {
    MainActivity activity;


    // 프래그먼트 화면 붙이기 : 반드시 onCreateView를 오버라이드 시켜야 한다

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.plusfragment,
                container, false);
        activity = (MainActivity) getActivity();

        viewGroup.findViewById(R.id.btn_test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onChangeFrag(3);
            }
        });

        return viewGroup;
    }
}
