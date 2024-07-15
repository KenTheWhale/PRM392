package com.ken.pettradingplatform;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.ken.pettradingplatform.Adapter.CustomerPostAdapter;
import com.ken.pettradingplatform.Adapter.ImagePagerAdapter;
import com.ken.pettradingplatform.configurations.APIClientConfig;
import com.ken.pettradingplatform.controllers.CustomerController;
import com.ken.pettradingplatform.reponses.CustomerPostListResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomepageActivity extends Activity {

    ImageButton btnSearch, btnNoti, btnProfile, btnSetting;
    TextView tvViewAllPosts, labelUsername, tvName;
    GridView gvPet;
    ArrayList<CustomerPostListResponse.Post> posts;
    CustomerController customerController;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        List<String> imageUrls = Arrays.asList(
                "https://images.fineartamerica.com/images/artworkimages/mediumlarge/1/various-dogs-horizontal-web-banner-susan-schmitz.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYjY-2FCPzIixwkFjNDBES38h0Qi3FZgstNg&s",
                "https://bestfriendspetcare.com/wp-content/uploads/2020/06/puppy-play-group-page-header-1.jpg"
        );

        ViewPager viewPager = findViewById(R.id.viewPager);
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, imageUrls);
        viewPager.setAdapter(adapter);

        btnSearch = findViewById(R.id.btn_search);
        btnNoti = findViewById(R.id.btn_noti);
        btnProfile = findViewById(R.id.btn_profile);
        btnSetting = findViewById(R.id.btn_setting);
        tvViewAllPosts = findViewById(R.id.tv_posts_view);
        labelUsername = findViewById(R.id.tv_username);
        tvName = findViewById(R.id.tv_username_holder);
        gvPet = findViewById(R.id.gv_pet);

        SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
        String accountID = sharedPref.getString("account", null);
        if(accountID == null){
            labelUsername.setVisibility(View.INVISIBLE);
            tvName.setVisibility(View.INVISIBLE);
        }
        btnSearch.setOnClickListener(v -> moveToAnotherPage(SearchActivity.class));

        tvViewAllPosts.setOnClickListener(v -> moveToAnotherPage(ViewPostsActivity.class));

        btnNoti.setOnClickListener(v -> moveToAnotherPage(NotificationActivity.class));

        btnProfile.setOnClickListener(v -> {
            SharedPreferences sharedPref1 = getSharedPreferences("session", Context.MODE_PRIVATE);
            String accountID1 = sharedPref1.getString("account", null);
            if(accountID1 == null){
                moveToLogin("homepage");
                return;
            }
            moveToProfile("homepage");
        });

        btnSetting.setOnClickListener(v -> moveToSetting("homepage"));

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
                        CustomerPostAdapter customerPostAdapter = new CustomerPostAdapter(HomepageActivity.this, R.layout.pest_post_customer, posts, "homepage");
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

    private void moveToSetting(String parent){
        Intent intent = new Intent(this, SettingActivity.class);
        intent.putExtra("parent", parent);
        startActivity(intent);
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
}
