package com.example.my17_fragment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SubFragment1_2 extends Fragment {
    MainActivity activity;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup4 = (ViewGroup) inflater.inflate(R.layout.subfragment1_2,
                container, false);
        activity = (MainActivity) getActivity();

        viewGroup4.findViewById(R.id.startBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onChangeFrag(5);
            }
        });


        return viewGroup4;
    }
}
