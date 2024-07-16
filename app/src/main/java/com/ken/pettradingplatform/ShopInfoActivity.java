package com.ken.pettradingplatform;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ken.pettradingplatform.configurations.APIClientConfig;
import com.ken.pettradingplatform.controllers.ShopController;
import com.ken.pettradingplatform.reponses.ShopProfileResponse;
import com.ken.pettradingplatform.requests.ShopProfileRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopInfoActivity extends Activity {
    Button sellerModeButton, profileButton, btnUpdateShopInfo;
    TextView shopNameEditText, addressShopProfi;
    ShopController shopController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_information);

        profileButton = findViewById(R.id.profileButton);
        sellerModeButton = findViewById(R.id.sellerModeButton);
        btnUpdateShopInfo = findViewById(R.id.btn_UpdateShopInfo);
        shopNameEditText = findViewById(R.id.shopNameEditText);
        addressShopProfi = findViewById(R.id.addressShopProfi);

        shopController = APIClientConfig.getClient().create(ShopController.class);
        SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
        String accountID = sharedPref.getString("account", null);
        if(accountID != null) {
            ShopProfileRequest request = ShopProfileRequest.builder().id(Integer.parseInt(accountID)).build();
            Call<ShopProfileResponse> responseCall = shopController.getShopByID(request);
            responseCall.enqueue(new Callback<ShopProfileResponse>() {
                @Override
                public void onResponse(Call<ShopProfileResponse> call, Response<ShopProfileResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ShopProfileResponse profileResponse = response.body();
                        if (profileResponse.getData() != null) {
                            ShopProfileResponse.DataResponse data = profileResponse.getData();
                            shopNameEditText.setText(data.getName());
                            addressShopProfi.setText(data.getAddress());
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to load shop information", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to load ", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ShopProfileResponse> call, Throwable t) {

                }
            });

        }

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopInfoActivity.this, ShopInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });

        sellerModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopInfoActivity.this, ShopProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnUpdateShopInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopInfoActivity.this, ShopProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
