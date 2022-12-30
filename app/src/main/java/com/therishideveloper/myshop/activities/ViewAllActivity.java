package com.therishideveloper.myshop.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.therishideveloper.myshop.adapters.ViewAllAdapter;
import com.therishideveloper.myshop.databinding.ActivityViewAllBinding;
import com.therishideveloper.myshop.models.ViewAllModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewAllActivity extends AppCompatActivity {

    private ActivityViewAllBinding binding;
    private List<ViewAllModel> viewAllModelList;
    private ViewAllAdapter viewAllAdapter;
    private FirebaseFirestore db;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewAllBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.progressBar.setVisibility(View.VISIBLE);
        binding.viewAllRv.setVisibility(View.GONE);

        String type = getIntent().getStringExtra("TYPE");

        db = FirebaseFirestore.getInstance();
        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(this, viewAllModelList);
        binding.viewAllRv.setAdapter(viewAllAdapter);

        if (type != null) {
            db.collection("AllProducts")
                    .whereEqualTo("type", type)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                ViewAllModel viewAllModel = snapshot.toObject(ViewAllModel.class);
                                viewAllModelList.add(viewAllModel);
                                viewAllAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(this, "Error..." + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                        binding.progressBar.setVisibility(View.GONE);
                        binding.viewAllRv.setVisibility(View.VISIBLE);
                    });
        }
    }
}