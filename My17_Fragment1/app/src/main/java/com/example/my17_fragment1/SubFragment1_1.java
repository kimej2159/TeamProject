package com.example.my17_fragment1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SubFragment1_1 extends Fragment {
    MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup3 = (ViewGroup) inflater.inflate(R.layout.subfragment1_1,
                container, false);
        activity = (MainActivity) getActivity();

        viewGroup3.findViewById(R.id.imgbtn_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onChangeFrag(5);
            }
        });


        return viewGroup3;
    }

}
