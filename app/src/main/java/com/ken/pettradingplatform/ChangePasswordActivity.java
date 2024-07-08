package com.ken.pettradingplatform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ChangePasswordActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_password);

        Button btnBack = findViewById(R.id.btn_back);
        EditText etOldPass = findViewById(R.id.et_old_password);
        EditText etNewPass = findViewById(R.id.et_new_password);
        EditText etConfirmNewPass = findViewById(R.id.et_confirm_new_password);
        Button btnConfirmChangePass = findViewById(R.id.btn_confirm);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePasswordActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnConfirmChangePass.setOnClickListener(new View.OnClickListener() {
            String oldPass = etOldPass.getText().toString();
            String newPass = etNewPass.getText().toString();
            String confirmNewPass = etConfirmNewPass.getText().toString();

            @Override
            public void onClick(View v) {
                changePassword(oldPass, newPass, confirmNewPass);
            }
        });
    }
    private void changePassword(String oldPass, String newPass, String confirmNewPass){

    }
}
