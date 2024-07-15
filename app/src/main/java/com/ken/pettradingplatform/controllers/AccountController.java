package com.ken.pettradingplatform.controllers;

import com.ken.pettradingplatform.reponses.LoginResponse;
import com.ken.pettradingplatform.reponses.RegisterResponse;
import com.ken.pettradingplatform.requests.LoginRequest;
import com.ken.pettradingplatform.requests.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountController {

    @POST("api/Accounts/Login/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("api/Accounts/register")
    Call<RegisterResponse> register(@Body RegisterRequest request);
}
