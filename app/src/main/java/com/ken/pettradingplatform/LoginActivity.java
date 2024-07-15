package com.ken.pettradingplatform;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ken.pettradingplatform.configurations.APIClientConfig;
import com.ken.pettradingplatform.controllers.AccountController;
import com.ken.pettradingplatform.reponses.LoginResponse;
import com.ken.pettradingplatform.requests.LoginRequest;

public class LoginActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = findViewById(R.id.btn_login);
        ImageButton btnBack = findViewById(R.id.btn_back);
        TextView tvCreateAccount = findViewById(R.id.tv_create_account);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAnotherPage(RegisterActivity.class);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAnotherPage(processParentClassBackButton());
            }
        });
    }

    private void login() {
        EditText txtEmail = findViewById(R.id.txt_email);
        EditText txtPass = findViewById(R.id.txt_password);

        String email = txtEmail.getText().toString();
        String pass = txtPass.getText().toString();

        AccountController controller = APIClientConfig.getClient().create(AccountController.class);
        LoginRequest request = LoginRequest.builder()
                .email(email)
                .password(pass)
                .build();

        Call<LoginResponse> response = controller.login(request);
        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                assert response.body() != null;
                if (response.body().getStatus().equals("200")) {
                    if(response.body().getData().getRole().equals("buyer")){
                        storeAccount(response.body().getData().getAccountID());
                        moveToAnotherPage(processParentClassBackButton());
                        return;
                    } else {
                        storeAccount(response.body().getData().getAccountID());
                        moveToAnotherPage(ShopInfoActivity.class);
                    }
                }
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Network failure", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void storeAccount(int accountID) {
        SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("account", accountID + "");
        editor.apply();
    }

    private Class<?> processParentClassBackButton(){
        String parent = getIntent().getStringExtra("parent");
        assert parent != null;
        switch (parent){
            case "homepage": return HomepageActivity.class;
            case "viewposts": return ViewPostsActivity.class;
            default: return LoginActivity.class;
        }
    }

    private void moveToAnotherPage(Class<?> classes){
        startActivity(new Intent(this, classes));
        finish();
    }
}

