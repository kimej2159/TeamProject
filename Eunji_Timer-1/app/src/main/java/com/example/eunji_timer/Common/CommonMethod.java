package com.example.eunji_timer.Common;


import com.example.eunji_timer.ASyncTask.ApiClient;
import com.example.eunji_timer.ASyncTask.ApiInterface;
import com.example.eunji_timer.DTO.MemberDTO;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class CommonMethod {

    // 어디에서나 접근이 가능하도록 static으로 정의해 놓는다
    public static String ipConfig = "http://192.168.0.50:8989/app/";
    public static MemberDTO loginDto = null;

    // 해당하는 getData라는 메소드를 여러 액티비티와 프래그먼트에서 재활용을 통해
    // Spring에 연결 후 데이터를 가지고 오는 처리를 해야함.
    // 어떤식으로 바뀌면 재활용이 편할까???
    private HashMap<String , Object> params = new HashMap<>();

    // 파라메터 데이터 넣는 매소드
    public void setParams(String key , Object value){
        params.put(key,value);
    }

    // 데이터 보내는 매소드 : "anJoin", 완료했을때 콜백 실행
    public void getData(String url , Callback<String> callback){
        ApiInterface apiInterface = new ApiClient().getApiClient().create(ApiInterface.class);
        Call<String> apiTest =  apiInterface.getData(url , params);
        apiTest.enqueue(callback);
    }

    // 데이터와 파일을 동시에 보낼때: "anJoin", 사진파일,   완료후 콜백
    public void sendFile(String url , MultipartBody.Part part, Callback<String> callback){
        ApiInterface apiInterface = new ApiClient().getApiClient().create(ApiInterface.class);
        Call<String> apiTest =  apiInterface.connFilesPost(url, stringToRequest(), part);
        apiTest.enqueue(callback);
    }

    //Multipart로 데이터 동시에 보내서 요청
    public RequestBody stringToRequest(){
        RequestBody data = null;
        if(!params.isEmpty()){
            data = RequestBody.create(
                    MediaType.parse("multipart/form-data"), new Gson().toJson(
                            params.get("param")
                    )
            );
        }
        return data;
    }



}
