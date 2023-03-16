package com.example.myteamproject_chanmin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myteamproject_chanmin.Common.CommonMethod;
import com.example.myteamproject_chanmin.DTO.MemberDTO;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFrag extends Fragment {

    private static final String TAG = "main:LoginFrag";
    
    EditText etID , etPW;
    Button btnLogin , btnJoin ;

    WelcomePage welcomePage;
    JoinFrag joinFrag;
    MemberDTO loginDto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup  = (ViewGroup) inflater.inflate(R.layout.loginpage ,
                container ,false);

        //메인페이지 객체생성
        welcomePage = new WelcomePage();
        //회원가입프래그먼트 객체생성
        joinFrag = new JoinFrag();

        //아이디 비밀번호 Edittext 초기화
        etID = viewGroup.findViewById(R.id.edt_login_id);
        etPW = viewGroup.findViewById(R.id.edt_login_pw);

        //회원가입
        btnJoin = viewGroup.findViewById(R.id.btn_login_joinBtn);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contain , joinFrag).commit();
            }
        });

        // 로그인
        btnLogin = viewGroup.findViewById(R.id.btn_login_loginBtn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etID.getText().toString();
                String pw = etPW.getText().toString();

                CommonMethod commonMethod = new CommonMethod();
                // 데이터를 파라메터로 보낸다 : 서버에서 받을때 id,pw로 받는다
                commonMethod.setParams("id", id);
                commonMethod.setParams("pw", pw);

                commonMethod.getData("anLogin", new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                            Log.d(TAG, "onResponse: " + response.body());

                            loginDto = new Gson().fromJson(response.body(), MemberDTO.class);

                            if (loginDto == null){
                                Toast.makeText(getActivity(), "아이디 혹은 비밀번호가 맞지 않습니다", Toast.LENGTH_LONG).show();

                             }else if ( loginDto.getId().equals(id)) {

                            Toast.makeText(getActivity(),
                                    loginDto.getId() + "님 반갑습니다!!!", Toast.LENGTH_SHORT).show();


                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contain,
                                    welcomePage).commit();

                        }

                    }


                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });

            }
        });



        return viewGroup;

    }
}
