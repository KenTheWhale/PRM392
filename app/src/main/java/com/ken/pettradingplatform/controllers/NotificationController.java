package com.ken.pettradingplatform.controllers;

import com.ken.pettradingplatform.reponses.CustomerPostListResponse;
import com.ken.pettradingplatform.reponses.NotificationResponse;
import com.ken.pettradingplatform.requests.NotificationRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NotificationController {
    @POST("api/Notifications/ViewNotificationByID")
    Call<NotificationResponse> getNotification(@Body NotificationRequest request);
}
