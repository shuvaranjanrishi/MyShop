package com.therishideveloper.myshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private Activity mActivity;
    private EditText nameEt,emailEt,passwordEt;
    private String name,email,password;
    private Button loginBtn;
    private TextView loginTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();

        initVariables();

        initListeners();
    }

    private void initViews() {
        nameEt = findViewById(R.id.nameEt);
        emailEt = findViewById(R.id.emailEt);
        passwordEt = findViewById(R.id.passwordEt);
        loginBtn = findViewById(R.id.loginBtn);
        loginTv = findViewById(R.id.loginTv);
    }

    private void initVariables() {
        mActivity = RegisterActivity.this;
    }

    private void initListeners() {
        loginTv.setOnClickListener(v -> loginTvClickAction());
    }

    private void loginTvClickAction() {
        startActivity(new Intent(mActivity, LoginActivity.class));
    }
}