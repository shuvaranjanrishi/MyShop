package com.therishideveloper.myshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private Activity mActivity;
    private EditText emailEt,passwordEt;
    private String email,password;
    private Button loginBtn;
    private TextView signUpTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        initVariables();

        initListeners();
    }

    private void initViews() {
        emailEt=findViewById(R.id.emailEt);
        passwordEt=findViewById(R.id.passwordEt);
        loginBtn=findViewById(R.id.loginBtn);
        signUpTv=findViewById(R.id.signUpTv);
    }

    private void initVariables() {
        mActivity=LoginActivity.this;
    }

    private void initListeners() {
        signUpTv.setOnClickListener(v->signUpTvClickAction());
    }

    private void signUpTvClickAction() {
        startActivity(new Intent(mActivity,RegisterActivity.class));
    }
}