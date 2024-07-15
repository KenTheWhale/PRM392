package com.ken.pettradingplatform;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ken.pettradingplatform.Adapter.CustomerPostAdapter;
import com.ken.pettradingplatform.Adapter.ShopPostAdapter;
import com.ken.pettradingplatform.configurations.APIClientConfig;
import com.ken.pettradingplatform.controllers.ShopController;
import com.ken.pettradingplatform.reponses.ShopPostListResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopProfileActivity extends Activity {
    Button btnProfile, btnSellerMode, btnAddPostShop, btnRequestMenu;

    GridView gvPostShop;
    ImageView imgBackground, imgProfile;

    ArrayList<ShopPostListResponse.Post> posts;
    TextView txtName, txtNumberSales, txtNumberRating, txtNumberFeedBack;

    ShopController shopController;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_profile);

        btnProfile = findViewById(R.id.profileButton);
        btnSellerMode = findViewById(R.id.sellerModeButton);
        btnAddPostShop = findViewById(R.id.btn_addPostShop);
        btnRequestMenu = findViewById(R.id.btn_menuRequest);

        imgBackground = findViewById(R.id.img_BackGroundShop);
        imgProfile = findViewById(R.id.profileImageView);

        txtName = findViewById(R.id.nameTextView);
        txtNumberSales = findViewById(R.id.txt_NumberSales);
        txtNumberRating = findViewById(R.id.txt_NumberRating);
        txtNumberFeedBack = findViewById(R.id.txt_NumberFeedBack);

        gvPostShop = findViewById(R.id.gv_postShop);

        shopController = APIClientConfig.getClient().create(ShopController.class);

    }

    public void initData(){
        posts = new ArrayList<>();
        Call<ShopPostListResponse> response = shopController.getAllPost();
        response.enqueue(new Callback<ShopPostListResponse>() {
            @Override
            public void onResponse(@NonNull Call<ShopPostListResponse> call, @NonNull Response<ShopPostListResponse> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().getStatus().equals("200")) {
                        posts = response.body().getData();
                        ShopPostAdapter shopPostAdapter = new ShopPostAdapter(ShopProfileActivity.this, R.layout.pest_post_shop, posts, "shopprofile");
                        gvPostShop.setAdapter(shopPostAdapter);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ShopPostListResponse> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Load fail", Toast.LENGTH_LONG).show();
            }
        });
    }
}
