package com.therishideveloper.myshop.adapters;

/*
    Created by Shuva Ranjan Rishi on 12/31/2022
*/

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.therishideveloper.myshop.databinding.ItemCartBinding;
import com.therishideveloper.myshop.models.CartModel;

import java.util.List;
import java.util.Objects;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final Context context;
    private final List<CartModel> cartModelList;
    private double inTotalPrice = 0.0;
    private final FirebaseAuth auth;
    private final FirebaseFirestore db;
    public CartItemListener cartItemListener;

    public CartAdapter(Context context, List<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCartBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartModel cartModel = cartModelList.get(position);

        holder.binding.productNameTv.setText("" + cartModel.getProductName());
        holder.binding.unitPriceTv.setText("" + cartModel.getProductPrice());
        holder.binding.quantityTv.setText("" + cartModel.getProductQuantity());
        double totalPrice = Double.parseDouble(cartModel.getTotalPrice());
        holder.binding.totalPriceTv.setText("" + totalPrice);

        inTotalPrice = inTotalPrice + totalPrice;

        holder.binding.deleteBtn.setOnClickListener(view -> db.collection("MyCart")
                .document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                .collection("CurrentUser")
                .document(cartModel.getDocumentId())
                .delete()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        cartModelList.remove(cartModel);
                        notifyDataSetChanged();
                        inTotalPrice = 0;
                        Toast.makeText(context, "Item Deleted...", Toast.LENGTH_SHORT).show();
                    }
                }));

        if (cartItemListener != null) {
            cartItemListener.onChange(cartModelList);
        }
    }

    @Override
    public int getItemCount() {
        return cartModelList == null ? 0 : cartModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ItemCartBinding binding;

        public ViewHolder(@NonNull ItemCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface CartItemListener {
        void onChange(List<CartModel> cartModelList);
    }

    public void setListener(CartItemListener cartItemListener) {
        this.cartItemListener = cartItemListener;
    }
}