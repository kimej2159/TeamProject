package com.example.myteamproject_chanmin.ASyncTask;

import com.bumptech.glide.request.Request;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ApiInterface {

    // get 또는 post 등 여러가지 방식의 요청을 미리 지정을 해놓고
    // 계속해서 새로 초기화해서 사용함.

    // 데이터만 보내고 응답(스트링) 받을때
    @FormUrlEncoded // URL을 가변적으로 바꿔서 요청 받게 처리(mapping)
    @POST // 요청방식 POST
    Call<String> getData(@Url String url , @FieldMap HashMap<String,Object> params);

    // 파일만 전송할때
    @POST("file.f")
    @Multipart
    Call<String> sendFile(@Part MultipartBody.Part file ); // @FieldMap 접속 되고 추가 (파라메터)

    // 데이터와 파일을 같이 보낼때
    @POST("{path}")     // localhost/middle/{path}
    @Multipart  // <--  @FormUrlEncoded 사용 금지됨, @Path 어노테이션을 써줘야함.
    Call<String> connFilesPost(@Path("path") String url
            , @Part("param") RequestBody param  //데이터 부분
            , @Part MultipartBody.Part file   // @Part List<MultipartBody.Part> file //여러개 파일부분
    );

//    public interface GitHubService {
//        @GET("users/{user}/repos")
//        Call<List<Repo>> listRepos(@Path("user") String user);
//    }
}
