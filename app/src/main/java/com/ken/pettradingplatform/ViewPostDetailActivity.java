package com.ken.pettradingplatform;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.ken.pettradingplatform.controllers.ShopController;
import com.ken.pettradingplatform.reponses.CustomerPostResponse;
import com.ken.pettradingplatform.requests.CreateReqRequest;
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
    ShopController shopController;

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

                        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
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
                                btnContact.setOnClickListener(v -> contactSeller());
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

        btnBack.setOnClickListener(v -> moveToAnotherPage(processParentClassBackButton()));
    }

    private void contactSeller(){

    }

    private void choosePet(){
        SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
        int postId = getIntent().getIntExtra("postId", -1);
        String accountId = sharedPref.getString("account", null);
        if(accountId != null && postId != -1){
            shopController = APIClientConfig.getClient().create(ShopController.class);
            CreateReqRequest request = CreateReqRequest.builder().accountId(Integer.parseInt(accountId)).postId(postId).build();
            Call<Void> response = shopController.createRequest(request);
            response.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ViewPostDetailActivity.this);
                    builder.setTitle("Request sent successfully!!")
                            .setMessage("Your request is now in pending, please wait for the latest notification")
                            .setPositiveButton("I have understood", (dialog, id) -> {
                                startActivity(new Intent(ViewPostDetailActivity.this, HomepageActivity.class));
                                finish();
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Load fail", Toast.LENGTH_LONG).show();
                }
            });
        }

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
