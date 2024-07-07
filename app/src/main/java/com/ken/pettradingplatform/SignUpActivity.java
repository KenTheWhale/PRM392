package com.ken.pettradingplatform;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ken.pettradingplatform.controllers.AccountController;
import com.ken.pettradingplatform.reponses.RegisterResponse;
import com.ken.pettradingplatform.requests.RegisterRequest;

import lombok.RequiredArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiredArgsConstructor
public class SignUpActivity extends AppCompatActivity {

    private final AccountController accountController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("SIGN UP");

        EditText fullNameET = findViewById(R.id.et_fullname);
        EditText emailET = findViewById(R.id.et_email);
        EditText passwordET = findViewById(R.id.et_password);
        EditText confirmedPasswordET = findViewById(R.id.et_confirm_password);
        Button signUpBTN = findViewById(R.id.btn_signup);

        String fullName = fullNameET.getText().toString();
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        String confirmedPassword = confirmedPasswordET.getText().toString();

        signUpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(fullName, email, password, confirmedPassword);
            }
        });
    }

    private void register(String fullName, String email, String password, String confirmedPassword){
        RegisterRequest request = RegisterRequest.builder()
                .fullname(fullName)
                .email(email)
                .password(password)
                .confirmPassword(confirmedPassword)
                .build();
        Call<RegisterResponse> response = accountController.register(request);
        response.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something was wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
