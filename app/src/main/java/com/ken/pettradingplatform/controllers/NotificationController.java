package com.ken.pettradingplatform.controllers;

import com.ken.pettradingplatform.reponses.CustomerPostListResponse;
import com.ken.pettradingplatform.reponses.NotificationResponse;
import com.ken.pettradingplatform.requests.NotificationRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface NotificationController {
    @GET("api/Notifications/ViewNotificationByID")
    public Call<NotificationResponse> getNotification(@Body NotificationRequest request);
}
