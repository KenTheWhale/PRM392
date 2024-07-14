package com.ken.pettradingplatform;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomepageActivity extends Activity {
    ImageButton test;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        test = findViewById(R.id.test);
        test.setOnClickListener(v-> test.setSelected(true));
    }
}
