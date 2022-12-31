package com.therishideveloper.myshop.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;
import com.therishideveloper.myshop.R;
import com.therishideveloper.myshop.databinding.ActivityProductDetailsBinding;
import com.therishideveloper.myshop.models.ViewAllModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProductDetailsActivity extends AppCompatActivity {

    private static final String TAG = "ProductDetailsActivity";

    private ActivityProductDetailsBinding binding;
    private ViewAllModel viewAllModel = null;
    private int quantity = 1;
    private double price = 0.0,totalPrice = 0.0;

    private FirebaseAuth auth;
    private FirebaseFirestore fireStore;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initVariables();

        getIntentData();

        initListeners();


    }

    private void initListeners() {
        binding.decrementBtn.setOnClickListener(view -> {
            if (quantity > 1) {
                binding.quantityTv.setText("" + (quantity--));
                totalPrice = quantity * price;
            }
        });

        binding.incrementBtn.setOnClickListener(view -> {
            if (quantity < 11) {
                binding.quantityTv.setText("" + (quantity++));
                totalPrice = price * quantity;
            }
        });

        binding.addToCartBtn.setOnClickListener(view -> {

            totalPrice = price * quantity;

            String currentDate, currentTime;
            Calendar calendar = Calendar.getInstance();

            SimpleDateFormat dateFormat =  new SimpleDateFormat("dd-MM-yyyy");
            currentDate = dateFormat.format(calendar.getTime());

            SimpleDateFormat timeFormat  =  new SimpleDateFormat("hh:mm:ss a");
            currentTime = timeFormat.format(calendar.getTime());

            Map<String,Object> map = new HashMap<>();
            map.put("productName",""+viewAllModel.getName());
            map.put("productPrice",""+price);
            map.put("productQuantity",""+quantity);
            map.put("totalPrice",""+totalPrice);
            map.put("currentDate",""+currentDate);
            map.put("currentTime",""+currentTime);

            saveData(map);
        });
    }

    private void getIntentData() {
        final Object object = getIntent().getSerializableExtra("PRODUCT_DETAILS");

        if (object instanceof ViewAllModel) {
            viewAllModel = (ViewAllModel) object;
        }

        if (viewAllModel != null) {
            Picasso.get()
                    .load(viewAllModel.getImageUrl())
                    .placeholder(R.drawable.ic_baseline_image_24)
                    .into(binding.productIv);
            price = Double.parseDouble(viewAllModel.getPrice());
            binding.priceTv.setText("$ " + viewAllModel.getPrice() + "/kg");

            if (viewAllModel.getType().equals("Milk"))
                binding.priceTv.setText("$ " + price + "/liter");
            if (viewAllModel.getType().equals("Eggs"))
                binding.priceTv.setText("$ " + price + "/dozen");
            if (viewAllModel.getType().equals("Drinks"))
                binding.priceTv.setText("$ " + price + "/bottle");
            binding.ratingTv.setText("$ " + viewAllModel.getRating());
            binding.descriptionTv.setText("$ " + viewAllModel.getDescription());
        }
    }

    private void initVariables() {
        auth = FirebaseAuth.getInstance();
        fireStore = FirebaseFirestore.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setTitle("Plz Wait...");
        dialog.setMessage("Adding to Cart");
    }

    private void saveData(Map<String, Object> map) {
        dialog.show();
        fireStore.collection("MyCart")
                .document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                .collection("CurrentUser")
                .add(map)
                .addOnCompleteListener(task -> {
                    dialog.dismiss();
                    if(task.isSuccessful()){
                        Toast.makeText(ProductDetailsActivity.this, "Added To Cart...", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Log.d(TAG,""+task.getException());
                    }
                });
    }
}