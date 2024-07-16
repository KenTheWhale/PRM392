package com.ken.pettradingplatform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

        Button btnRegister = findViewById(R.id.btnRegister);
        EditText etEmail = findViewById(R.id.emailEditText);
        EditText etPass = findViewById(R.id.passwordEditText);
        EditText etConfirmPass = findViewById(R.id.confirmPasswordEditText);
        CheckBox checkBox = findViewById(R.id.termsCheckBox);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String pass = etPass.getText().toString();
                String confirm = etConfirmPass.getText().toString();

                if (!email.isEmpty() && !pass.isEmpty() && !confirm.isEmpty() && checkBox.isChecked()) {
                    register(email, pass, confirm);
                } else {
                    Toast.makeText(RegisterActivity.this, "Please fill all fields and accept the terms", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void register(String email, String pass, String confirm) {
        AccountController controller = APIClientConfig.getClient().create(AccountController.class);

        RegisterRequest request = RegisterRequest.builder()
                .fullName("NewUser")
                .email(email)
                .password(pass)
                .confirmPassword(confirm)
                .build();

        Call<RegisterResponse> response = controller.register(request);
        response.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse responseBody = response.body();
                    if (responseBody != null) {
                        String status = responseBody.getStatus();
                        String message = responseBody.getMessage();

                        if ("200".equals(status)) {
                            moveToLogin();
                        } else {
                            Log.e("RegisterActivity", "Registration failed with status: " + status + ", message: " + message);
                            Toast.makeText(getApplicationContext(), message != null ? message : "Failed to register", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("RegisterActivity", "Registration failed: Response body is null");
                        Toast.makeText(getApplicationContext(), "Failed to register: Empty response", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("RegisterActivity", "Registration failed: " + response.code() + " " + response.message());
                    Toast.makeText(getApplicationContext(), "Failed to register: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                Log.e("RegisterActivity", "Network failure", t);
                Toast.makeText(getApplicationContext(), "Network failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void moveToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
