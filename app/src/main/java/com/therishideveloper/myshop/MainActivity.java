package com.therishideveloper.myshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Activity mActivity;
    private LinearLayout loginLl, registerLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initVariables();

        initListeners();
    }

    private void initViews() {
        loginLl = findViewById(R.id.loginLl);
        registerLl = findViewById(R.id.registerLl);
    }

    private void initVariables() {
        mActivity = MainActivity.this;
    }

    private void initListeners() {
        loginLl.setOnClickListener(v -> loginLlClickAction());
        registerLl.setOnClickListener(v -> registerLlClickAction());
    }

    private void loginLlClickAction() {
        startActivity(new Intent(mActivity, LoginActivity.class));
    }

    private void registerLlClickAction() {
        startActivity(new Intent(mActivity, RegisterActivity.class));
    }
}