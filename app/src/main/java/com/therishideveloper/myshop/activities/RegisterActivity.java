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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.therishideveloper.myshop.R;
import com.therishideveloper.myshop.models.UserModel;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private Activity mActivity;
    private EditText nameEt, emailEt, passwordEt;
    private String name, email, password;
    private Button singUpBtn;
    private TextView loginTv;

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private ProgressDialog dialog;

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
        singUpBtn = findViewById(R.id.singUpBtn);
        loginTv = findViewById(R.id.loginTv);
    }

    private void initVariables() {
        mActivity = RegisterActivity.this;
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        dialog = new ProgressDialog(mActivity);
        dialog.setTitle("Plz Wait...");
        dialog.setMessage("Register in Progress");
    }

    private void initListeners() {
        loginTv.setOnClickListener(v -> loginTvClickAction());
        singUpBtn.setOnClickListener(v -> singUpBtnClickAction());
    }

    private void loginTvClickAction() {
        startActivity(new Intent(mActivity, LoginActivity.class));
    }

    private void singUpBtnClickAction() {
        name = nameEt.getText().toString().trim();
        email = emailEt.getText().toString().trim();
        password = passwordEt.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            nameEt.setError("Name is Empty!");
            return;
        }
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

        createUser();
    }

    private void createUser() {
        dialog.show();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        saveUserInfo(Objects.requireNonNull(task.getResult().getUser()).getUid());

                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(mActivity, "Registration Failed...", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                });
    }

    private void saveUserInfo(String id) {

        dialog.setMessage("Saving Info...");
        UserModel userModel = new UserModel(id, name, email, password);

        DatabaseReference userRef = database.getReference("Users");
        userRef.child("Admin").child(id).setValue(userModel)
                .addOnSuccessListener(aVoid -> {
                    dialog.dismiss();
                    Toast.makeText(mActivity, "Registration Complete...", Toast.LENGTH_LONG).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(mActivity, "Registration Failed...", Toast.LENGTH_LONG).show();
                });
    }
}