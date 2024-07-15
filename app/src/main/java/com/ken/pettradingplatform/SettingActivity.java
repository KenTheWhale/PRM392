package com.ken.pettradingplatform;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class SettingActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ImageButton btnBack = findViewById(R.id.backButton);
        Button btnLogout = findViewById(R.id.btn_logout);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAnotherPage(processParentClassBackButton());
            }
        });

        SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
        String accountID = sharedPref.getString("account", null);
        if(accountID != null){
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.clear();
                    editor.apply();
                    moveToAnotherPage(HomepageActivity.class);
                }
            });
        }else{
            btnLogout.setVisibility(View.INVISIBLE);
        }
    }

    private Class<?> processParentClassBackButton(){
        String parent = getIntent().getStringExtra("parent");
        assert parent != null;
        switch (parent){
            case "homepage": return HomepageActivity.class;
            case "viewposts": return ViewPostsActivity.class;
            default: return SettingActivity.class;
        }
    }

    private void moveToAnotherPage(Class<?> classes){
        startActivity(new Intent(this, classes));
        finish();
    }
}
