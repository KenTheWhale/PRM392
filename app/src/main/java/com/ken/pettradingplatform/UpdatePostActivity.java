package com.ken.pettradingplatform;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class UpdatePostActivity extends Activity {

    ImageButton btnBackButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_post);

        btnBackButton = findViewById(R.id.backButton);

        btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAnotherPage(processParentClassBackButton());
            }
        });

    }

    private Class<?> processParentClassBackButton(){
        String parent = getIntent().getStringExtra("parent");
        assert parent != null;
        switch (parent){
            case "shopprofile": return SearchActivity.class;
            default: return UpdatePostActivity.class;
        }
    }

    private void moveToAnotherPage(Class<?> classes){
        startActivity(new Intent(this, classes));
        finish();
    }
}
