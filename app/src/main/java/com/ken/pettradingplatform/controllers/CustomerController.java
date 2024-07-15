package com.ken.pettradingplatform.controllers;

import com.ken.pettradingplatform.reponses.CustomerPostListResponse;
import com.ken.pettradingplatform.reponses.CustomerPostResponse;
import com.ken.pettradingplatform.requests.CustomerPostRequest;

import retrofit2.Call;
import retrofit2.http.*;

public interface CustomerController {

    @GET("api/Posts/ViewAllPost")
    Call<CustomerPostListResponse> getAllPost();

    @POST("api/Posts/ViewPostByID")
    Call<CustomerPostResponse> getPostById(@Body CustomerPostRequest request);
}
