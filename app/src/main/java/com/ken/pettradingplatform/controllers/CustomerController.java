package com.ken.pettradingplatform.controllers;

import com.ken.pettradingplatform.reponses.CustomerPostListResponse;

import retrofit2.Call;
import retrofit2.http.*;

public interface CustomerController {

    @GET("api/Posts/ViewAllPost")
    public Call<CustomerPostListResponse> getAllPost();
}
