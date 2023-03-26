package com.example.myteamproject_chanmin;

import static com.example.myteamproject_chanmin.Common.CommonMethod.ipConfig;
import static com.example.myteamproject_chanmin.Common.CommonMethod.loginDto;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myteamproject_chanmin.Adapter.GymAdapter;
import com.example.myteamproject_chanmin.Common.CommonMethod;
import com.example.myteamproject_chanmin.DTO.GymDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CenterFind extends Fragment {
    private static final String TAG = "main:SubActivity";

    ArrayList<GymDTO> dtos;
    GymAdapter adapter;
    Button centerFindBtn;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.centerfind,
                container, false);

        centerFindBtn =  viewGroup.findViewById(R.id.centerFindBtn);
        centerFindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 반드시 생성해서 어댑터에 넘겨야 함
                dtos = new ArrayList<>();
                recyclerView = viewGroup.findViewById(R.id.recyclerView);
                // recyclerView에서 반드시 아래와 같이 초기화를 해줘야 함
                LinearLayoutManager layoutManager = new
                        LinearLayoutManager(
                        getActivity(), RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);

                // 서버에 member들 데이터를 요청
                CommonMethod commonMethod = new CommonMethod();
                commonMethod.setParams("1" , "111123");
                commonMethod.getData("selectMembers", new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                            Log.d(TAG, "onResponse: " + response.body());

                            // 서버에서 넘어온 데이터를 받는다
                            Gson gson = new Gson();
                            dtos = gson.fromJson(response.body(),
                                    new TypeToken<ArrayList<GymDTO>>() {}.getType());

                            // 제대로 데이터가 입력됐는지 확인
                            for(GymDTO dto : dtos){
                                // profile 경로를 적어준다
                                dto.setGym_picture(ipConfig + "resources/" + dto.getGym_picture());
                                Log.d(TAG, "사진 경로" + dto.getGym_picture());
                            }

                            Log.d(TAG, "onResponse: dtos.size() => " + dtos.size());

                            // 어댑터 객체 생성
                            adapter = new GymAdapter(getActivity(), dtos);
                            recyclerView.setAdapter(adapter);
                            //adapter.notifyDataSetChanged();




                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

            }
        });

        return viewGroup;
    }
}
