package com.ken.pettradingplatform;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ken.pettradingplatform.Adapter.ImagePagerAdapter;

import java.util.Arrays;
import java.util.List;

public class HomepageActivity extends Activity {

    ImageButton btnSearch, btnNoti;
    TextView tvViewAllPosts;
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
        tvViewAllPosts = findViewById(R.id.tv_posts_view);
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
    }

    private void moveToAnotherPage(Class<?> classes){
        startActivity(new Intent(this, classes));
        finish();
    }
}
