package com.therishideveloper.myshop.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.therishideveloper.myshop.R;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private Activity mActivity;
    private EditText emailEt, passwordEt;
    private String email, password;
    private Button loginBtn;
    private TextView signUpTv;

    private FirebaseAuth auth;
    private ProgressDialog dialog;

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
        mActivity = LoginActivity.this;
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(mActivity);
        dialog.setTitle("Plz Wait...");
        dialog.setMessage("Login Progress");
    }

    private void initListeners() {
        signUpTv.setOnClickListener(v -> signUpTvClickAction());
        loginBtn.setOnClickListener(v -> loginBtnClickAction());
    }

    private void signUpTvClickAction() {
        startActivity(new Intent(mActivity, RegisterActivity.class));
    }

    private void loginBtnClickAction() {
        email = emailEt.getText().toString().trim();
        password = passwordEt.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailEt.setError("Email is Empty!");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEt.setError("Password is Empty!");
            return;
        }
        if (password.length() < 6) {
            passwordEt.setError("Password should be at least 6 Character!");
            return;
        }

        loginUser();
    }

    private void loginUser() {
        dialog.show();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(mActivity, HomeActivity.class));
                        Toast.makeText(mActivity, "Login Success...", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    dialog.dismiss();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(mActivity, "Login Failed...", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                });

    }
}