package com.example.myteamproject_chanmin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myteamproject_chanmin.ASyncTask.ApiInterface;
import com.example.myteamproject_chanmin.Common.CommonMethod;
import com.example.myteamproject_chanmin.DTO.MemberDTO;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class JoinFrag extends Fragment {

    private static final String TAG = "main: JoinFrag";

    // 서버에보내야할 내용들
    EditText id, pw, name, email, phone;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.joinpage
                , container, false);

        id = viewGroup.findViewById(R.id.edt_join_id);
        pw = viewGroup.findViewById(R.id.edt_join_pw);
       name = viewGroup.findViewById(R.id.edt_join_name);
      email = viewGroup.findViewById(R.id.edt_join_email);
      phone = viewGroup.findViewById(R.id.edt_join_tel);


        //가입하기 : 회원의 정보(내용)을 가져와서 서버에 보낸다
        viewGroup.findViewById(R.id.btn_join_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemberDTO dto = new MemberDTO();
                dto.setId(id.getText().toString());
                dto.setPw(pw.getText().toString());
                dto.setName(name.getText().toString());
                dto.setEmail(email.getText().toString());
                dto.setPhone(phone.getText().toString());



                CommonMethod commonMethod = new CommonMethod();
                // 데이터를 파라메터로 보낸다 : 서버에서 받을때 id,pw로 받는다

                commonMethod.setParams("id", dto.getId());
                commonMethod.setParams("pw", dto.getPw());
                commonMethod.setParams("name", dto.getName());
                commonMethod.setParams("email", dto.getEmail());
                commonMethod.setParams("phone", dto.getPhone());




                commonMethod.getData("anJoin", new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG, "onResponse: 삽입성공" + response.body());



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
