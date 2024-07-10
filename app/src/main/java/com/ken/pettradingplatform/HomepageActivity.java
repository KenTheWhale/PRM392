package com.ken.pettradingplatform;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ken.pettradingplatform.Adapter.ImagePagerAdapter;

import java.util.Arrays;
import java.util.List;

public class HomepageActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        List<String> imageUrls = Arrays.asList(
                "https://vcdn1-dulich.vnecdn.net/2021/07/16/1-1626437591.jpg?w=460&h=0&q=100&dpr=2&fit=crop&s=i2M2IgCcw574LT-bXFY92g",
                "https://d1hjkbq40fs2x4.cloudfront.net/2017-08-21/files/landscape-photography_1645-t.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSrE1y3Fi9zZvMeTT7VCXbk3vqKRKTGLyb9fA&s"
        );

        ViewPager viewPager = findViewById(R.id.viewPager);
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, imageUrls);
        viewPager.setAdapter(adapter);
    }
}
