package com.ken.pettradingplatform.controllers;

import com.ken.pettradingplatform.reponses.ShopPostListResponse;
import com.ken.pettradingplatform.reponses.ShopProfileResponse;
import com.ken.pettradingplatform.requests.ShopProfileRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;

public interface ShopController {
    @GET("api/Posts/ViewAllPost")
    public Call<ShopPostListResponse> getAllPost();

    @DELETE("api/Posts/CancelPost")
    public Call<ShopPostListResponse> cancelPost();

    @GET("api/Posts/ViewAllPost")
    public Call<ShopProfileResponse> getShopByID(@Body ShopProfileRequest request);
}
