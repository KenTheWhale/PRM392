package com.ken.pettradingplatform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                moveToRegister();
            }
        });
    }

    private void login(){
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
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                assert response.body() != null;
                if(response.body().getStatus().equals("200")){
                    moveToHomepage(response.body().getAccount());
                    return;
                }
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Network failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void moveToHomepage(LoginResponse.Account account){
        Intent i = new Intent(this, HomepageActivity.class);
        i.putExtra("acc", account);
        startActivity(i);
        finish();
    }

    private void moveToRegister(){
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
