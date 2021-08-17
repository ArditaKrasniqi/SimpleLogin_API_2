package com.example.simplelogin_api_2;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("/api/users")
    Call<User> getUserInformation(@Field("name") String name, @Field("job") String job);

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse> getLoginInformation(@Field("email") String email, @Field("password")String password);

    @FormUrlEncoded
    @POST("/api/register")
    Call<RegisterResponse> getRegisterInformation(@Field("email") String email, @Field("password")String password);

    @GET("/api/users?page=2")
    Call<User> getData();

}
