package com.ken.pettradingplatform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ken.pettradingplatform.R;
import com.ken.pettradingplatform.configurations.APIClientConfig;
import com.ken.pettradingplatform.controllers.AccountController;
import com.ken.pettradingplatform.reponses.RegisterResponse;
import com.ken.pettradingplatform.requests.RegisterRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnRegister = findViewById(R.id.getStartedButton);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void register(){
        AccountController controller = APIClientConfig.getClient().create(AccountController.class);
        EditText etFullName = findViewById(R.id.fullNameEditText);
        EditText etEmail = findViewById(R.id.emailEditText);
        EditText etPass = findViewById(R.id.passwordEditText);
        EditText etConfirmPass = findViewById(R.id.confirmPasswordEditText);

        String fullName = etFullName.getText().toString();
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        String confirm = etConfirmPass.getText().toString();

        RegisterRequest request = RegisterRequest.builder()
                .fullName(fullName)
                .email(email)
                .password(pass)
                .confirmPassword(confirm)
                .build();

        Call<RegisterResponse> response = controller.register(request);
        response.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.body().getStatus().equals("200")){
                    moveToLogin();
                    return;
                }
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Network failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void moveToLogin(){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
