package com.ken.pettradingplatform;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.ken.pettradingplatform.Adapter.ImagePagerAdapter;

import java.util.Arrays;
import java.util.List;

public class HomepageActivity extends Activity {

    ImageButton btnSearch, btnNoti, btnProfile, btnSetting;
    TextView tvViewAllPosts, labelUsername, tvName;

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

        SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
        String accountID = sharedPref.getString("account", null);
        if(accountID == null){
            labelUsername.setVisibility(View.INVISIBLE);
            tvName.setVisibility(View.INVISIBLE);
        }
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAnotherPage(SearchActivity.class);
            }
        });

        tvViewAllPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAnotherPage(ViewPostsActivity.class);
            }
        });

        btnNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAnotherPage(NotificationActivity.class);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
                String accountID = sharedPref.getString("account", null);
                if(accountID == null){
                    moveToLogin("homepage");
                    return;
                }
                moveToProfile("homepage");
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToSetting("homepage");
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
