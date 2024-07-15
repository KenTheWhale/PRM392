package com.ken.pettradingplatform;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ken.pettradingplatform.configurations.APIClientConfig;
import com.ken.pettradingplatform.controllers.CustomerController;
import com.ken.pettradingplatform.reponses.CustomerPostResponse;
import com.ken.pettradingplatform.requests.CustomerPostRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPostDetailActivity extends Activity {

    ImageButton btnBack;
    ImageView image;
    TextView name, description, price;
    CheckBox checkBox;
    Button btnContact;
    CustomerController customerController;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        btnBack = findViewById(R.id.backButton);
        image = findViewById(R.id.productImageView);
        name = findViewById(R.id.productNameTextView);
        description = findViewById(R.id.productDescriptionTextView);
        price = findViewById(R.id.priceTextView);
        checkBox = findViewById(R.id.cb_agree);
        btnContact = findViewById(R.id.contactButton);

        image.setImageResource(R.drawable.dog);
        customerController = APIClientConfig.getClient().create(CustomerController.class);
        CustomerPostRequest request = CustomerPostRequest.builder()
                .id(getIntent().getIntExtra("postId", -1))
                .build();
        Call<CustomerPostResponse> response = customerController.getPostById(request);
        response.enqueue(new Callback<CustomerPostResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<CustomerPostResponse> call, @NonNull Response<CustomerPostResponse> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().getStatus().equals("200")) {
                        CustomerPostResponse.Post post = response.body().getData();
                        name.setText(post.getTypeName());
                        description.setText(post.getDescription());
                        price.setText("$" + post.getPrice());

                        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked){
                                    btnContact.setText("Pick it now");
                                    btnContact.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            choosePet();
                                        }
                                    });
                                }else{
                                    btnContact.setText("Contact seller");
                                    btnContact.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            contactSeller();
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<CustomerPostResponse> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Load fail", Toast.LENGTH_LONG).show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAnotherPage(processParentClassBackButton());
            }
        });
    }

    private void contactSeller(){

    }

    private void choosePet(){

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
