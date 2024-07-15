package com.ken.pettradingplatform;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ken.pettradingplatform.Adapter.CustomerPostAdapter;
import com.ken.pettradingplatform.configurations.APIClientConfig;
import com.ken.pettradingplatform.controllers.CustomerController;
import com.ken.pettradingplatform.reponses.CustomerPostListResponse;

import java.util.ArrayList;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPostsActivity extends Activity {

    ImageButton btnBack, btnHome, btnProfile, btnSetting;
    ArrayList<CustomerPostListResponse.Post> posts;
    CustomerController customerController;
    GridView gvPet;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_post);
        btnBack = findViewById(R.id.backButton);
        btnHome = findViewById(R.id.btn_home);
        btnProfile = findViewById(R.id.btn_profile);
        btnSetting =  findViewById(R.id.btn_setting);
        gvPet = findViewById(R.id.gv_pet);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAnotherPage(HomepageActivity.class);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAnotherPage(HomepageActivity.class);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
                String accountID = sharedPref.getString("account", null);
                if(accountID == null){
                    moveToLogin("viewposts");
                    return;
                }
                moveToProfile("viewposts");
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToSetting("viewposts");
            }
        });

        customerController = APIClientConfig.getClient().create(CustomerController.class);
        initData();
    }

    public void initData(){
        posts = new ArrayList<>();
        Call<CustomerPostListResponse> response = customerController.getAllPost();
        response.enqueue(new Callback<CustomerPostListResponse>() {
            @Override
            public void onResponse(@NonNull Call<CustomerPostListResponse> call, @NonNull Response<CustomerPostListResponse> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().getStatus().equals("200")) {
                        posts = response.body().getData();
                        CustomerPostAdapter customerPostAdapter = new CustomerPostAdapter(ViewPostsActivity.this, R.layout.pest_post_customer, posts, "viewposts");
                        gvPet.setAdapter(customerPostAdapter);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<CustomerPostListResponse> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Load fail", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void moveToAnotherPage(Class<?> classes){
        startActivity(new Intent(this, classes));
        finish();
    }

    private void moveToLogin(String parent){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("parent", parent);
        startActivity(intent);
        finish();
    }

    private void moveToProfile(String parent){
        Intent intent = new Intent(this, ProfileUserActivity.class);
        intent.putExtra("parent", parent);
        startActivity(intent);
        finish();
    }

    private void moveToSetting(String parent){
        Intent intent = new Intent(this, SettingActivity.class);
        intent.putExtra("parent", parent);
        startActivity(intent);
        finish();
    }
}
