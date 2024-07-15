package com.ken.pettradingplatform;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class ViewPostsActivity extends Activity {

    ImageButton btnBack, btnHome, btnProfile, btnSetting;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_post);
        btnBack = findViewById(R.id.backButton);
        btnHome = findViewById(R.id.btn_home);
        btnProfile = findViewById(R.id.btn_profile);
        btnSetting =  findViewById(R.id.btn_setting);
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
