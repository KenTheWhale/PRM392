package com.ken.pettradingplatform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class ViewPostDetailActivity extends Activity {

    ImageButton btnBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

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
        switch (parent){
            case "homepage": return HomepageActivity.class;
            case "viewposts": return ViewPostsActivity.class;
            case "search": return SearchActivity.class;
            default: return ViewPostsActivity.class;
        }
    }

    private void moveToAnotherPage(Class<?> classes){
        startActivity(new Intent(this, classes));
        finish();
    }
}
