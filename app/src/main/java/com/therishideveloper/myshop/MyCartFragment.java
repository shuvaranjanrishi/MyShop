package com.therishideveloper.myshop;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.therishideveloper.myshop.adapters.CartAdapter;
import com.therishideveloper.myshop.databinding.FragmentMyCartBinding;
import com.therishideveloper.myshop.models.CartModel;
import com.therishideveloper.myshop.utils.DialogUtils;
import com.therishideveloper.myshop.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MyCartFragment extends Fragment {

    private static final String TAG = "MyCartFragment";

    private FragmentMyCartBinding binding;

    private List<CartModel> cartModelList;
    private CartAdapter cartAdapter;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @SuppressLint("NotifyDataSetChanged")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyCartBinding.inflate(inflater, container, false);

        initVariables();

        getCartData();

        binding.buyNowBtn.setOnClickListener(view -> {

            if (cartModelList != null && cartModelList.size() > 0) {
                for (CartModel item : cartModelList) {
                    Map<String, String> map = new HashMap<>();
                    map.put("productName", "" + item.getProductName());
                    map.put("productPrice", "" + item.getProductPrice());
                    map.put("productQuantity", "" + item.getProductQuantity());
                    map.put("totalPrice", "" + item.getTotalPrice());

                    db.collection("Orders")
                            .document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                            .collection("Items")
                            .add(map)
                            .addOnCompleteListener(task -> Toast.makeText(getActivity(), "Order Submitted...", Toast.LENGTH_SHORT).show());
                }
            }
        });

        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getCartData() {
        if (!NetworkUtils.checkInternet(requireActivity())) {
            DialogUtils.showNoInternetDialog(requireActivity());
            return;
        }
        binding.progressBar.setVisibility(View.VISIBLE);

        db.collection("MyCart")
                .document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                .collection("CurrentUser")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot snapshot : task.getResult().getDocuments()) {
                            CartModel cartModel = snapshot.toObject(CartModel.class);
                            assert cartModel != null;
                            cartModel.setDocumentId(snapshot.getId());
                            cartModelList.add(cartModel);
                            cartAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Error..." + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                    binding.progressBar.setVisibility(View.GONE);
                    binding.cartRv.setVisibility(View.VISIBLE);
                    binding.footerRl.setVisibility(View.VISIBLE);

                    if (cartModelList == null || cartModelList.size() == 0) {
                        binding.emptyCartCl.setVisibility(View.VISIBLE);
                        binding.footerRl.setVisibility(View.GONE);
                    }

                });

        CartAdapter.CartItemListener listener = (totalItems, inTotalPrice) -> {
            binding.totalItemTv.setText("Total Items: " + totalItems);
            binding.totalPriceTv.setText("Total Price: $" + inTotalPrice);
        };
        cartAdapter.setListener(listener);


//        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                int totalItems = intent.getIntExtra("TOTAL_ITEMS", 0);
//                double inTotalPrice = intent.getDoubleExtra("IN_TOTAL_PRICE", 0);
//                binding.totalItemTv.setText("Total Items: " + totalItems);
//                binding.totalPriceTv.setText("Total Price: $" + inTotalPrice);
//            }
//        };
//
//        LocalBroadcastManager.getInstance(requireActivity()).registerReceiver(broadcastReceiver, new IntentFilter("MY_IN_TOTAL_PRICE"));

    }

    private void initVariables() {
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        cartModelList = new ArrayList<>();
        cartAdapter = new CartAdapter(getActivity(), cartModelList);
        binding.cartRv.setAdapter(cartAdapter);
        binding.footerRl.setVisibility(View.GONE);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}