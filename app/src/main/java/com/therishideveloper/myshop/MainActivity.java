package com.therishideveloper.myshop;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Activity mActivity;
    private LinearLayout loginLl, registerLl;

    private FirebaseAuth auth;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initVariables();

        autoLogin();

        initListeners();
    }

    private void autoLogin() {
        if(auth.getCurrentUser()!=null)  {
            startActivity(new Intent(mActivity, HomeActivity.class));
            finish();
        }
    }

    private void initViews() {
        loginLl = findViewById(R.id.loginLl);
        registerLl = findViewById(R.id.registerLl);
    }

    private void initVariables() {
        mActivity = MainActivity.this;
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(mActivity);
        dialog.setTitle("Plz Wait...");
        dialog.setMessage("You are already Logged In");
    }

    private void initListeners() {
        loginLl.setOnClickListener(v -> loginLlClickAction());
        registerLl.setOnClickListener(v -> registerLlClickAction());
    }

    private void loginLlClickAction() {
        startActivity(new Intent(mActivity, LoginActivity.class));
        finish();
    }

    private void registerLlClickAction() {
        startActivity(new Intent(mActivity, RegisterActivity.class));
        finish();
    }
}