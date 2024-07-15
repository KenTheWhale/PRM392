package com.ken.pettradingplatform.controllers;

import com.ken.pettradingplatform.reponses.ShopPostListResponse;
import com.ken.pettradingplatform.reponses.ShopProfileResponse;
import com.ken.pettradingplatform.requests.ShopProfileRequest;
import com.ken.pettradingplatform.requests.CreateReqRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ShopController {
    @GET("api/Posts/ViewAllPost")
    Call<ShopPostListResponse> getAllPost();

    @DELETE("api/Posts/CancelPost")
    Call<ShopPostListResponse> cancelPost();

    @POST("api/Requests/CreateRequest")
    Call<Void> createRequest(@Body CreateReqRequest request);
    public Call<ShopPostListResponse> cancelPost();

    @GET("api/Posts/ViewAllPost")
    public Call<ShopProfileResponse> getShopByID(@Body ShopProfileRequest request);
}
