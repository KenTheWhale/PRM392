package com.ken.pettradingplatform;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class SettingActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ImageButton btnBack = findViewById(R.id.backButton);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAnotherPage(processParentClassBackButton());
            }
        });
    }

    private Class<?> processParentClassBackButton(){
        String parent = getIntent().getStringExtra("parent");
        assert parent != null;
        if (parent.equals("homepage")) {
            return HomepageActivity.class;
        }
        return SettingActivity.class;
    }

    private void moveToAnotherPage(Class<?> classes){
        startActivity(new Intent(this, classes));
        finish();
    }
}
