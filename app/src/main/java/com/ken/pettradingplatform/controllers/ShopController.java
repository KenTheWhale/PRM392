package com.ken.pettradingplatform.controllers;

import com.ken.pettradingplatform.reponses.ShopPostListResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;

public interface ShopController {
    @GET("api/Posts/ViewAllPost")
    public Call<ShopPostListResponse> getAllPost();

    @DELETE("api/Posts/CancelPost")
    public Call<ShopPostListResponse> cancelPost();
}
