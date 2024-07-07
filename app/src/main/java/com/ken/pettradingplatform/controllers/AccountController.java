package com.ken.pettradingplatform.controllers;

import com.ken.pettradingplatform.reponses.LoginResponse;
import com.ken.pettradingplatform.reponses.RegisterResponse;
import com.ken.pettradingplatform.requests.LoginRequest;
import com.ken.pettradingplatform.requests.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountController {

    @POST("account/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("account/register")
    Call<RegisterResponse> register(@Body RegisterRequest request);
}
