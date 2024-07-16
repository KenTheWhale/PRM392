package com.ken.pettradingplatform;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ken.pettradingplatform.Adapter.NotificationAdapter;
import com.ken.pettradingplatform.configurations.APIClientConfig;
import com.ken.pettradingplatform.controllers.NotificationController;
import com.ken.pettradingplatform.reponses.NotificationResponse;
import com.ken.pettradingplatform.requests.NotificationRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends Activity {

    private ImageButton btnBack;
    private ListView lvNotification;
    private NotificationController notificationController;
    private ArrayList<NotificationResponse.Notification> notifications;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        btnBack = findViewById(R.id.backButton);
        lvNotification = findViewById(R.id.lv_notification);
        notificationController = APIClientConfig.getClient().create(NotificationController.class);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAnotherPage(HomepageActivity.class);
            }
        });

        InitData(); // Gọi phương thức này để khởi tạo dữ liệu
    }

    private void moveToAnotherPage(Class<?> classes) {
        startActivity(new Intent(this, classes));
        finish();
    }

    private void InitData() {
        notifications = new ArrayList<>();
        SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
        String accountID = sharedPref.getString("account", null);

        if (accountID != null) {
            NotificationRequest request = NotificationRequest.builder().id(Integer.parseInt(accountID)).build();
            Call<NotificationResponse> response = notificationController.getNotification(request);
            response.enqueue(new Callback<NotificationResponse>() {
                @Override
                public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        Log.d("API Response", "Response body: " + response.body());
                        if (response.body().getStatus().equals("200")) {
                            notifications = response.body().getData();
                            Log.d("Notifications", "Size: " + notifications.size());
                            NotificationAdapter notificationAdapter = new NotificationAdapter(NotificationActivity.this, R.layout.notification_item, notifications, "notification");
                            lvNotification.setAdapter(notificationAdapter);
                        } else {
                            Toast.makeText(getApplicationContext(), "Error: " + response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Response not successful", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<NotificationResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Load fail: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Account ID is null", Toast.LENGTH_LONG).show();
        }
    }
}
