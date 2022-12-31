package com.therishideveloper.myshop.adapters;

/*
    Created by Shuva Ranjan Rishi on 12/31/2022
*/

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.therishideveloper.myshop.R;
import com.therishideveloper.myshop.models.CartModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final Context context;
    private final List<CartModel> cartModelList;
    private double inTotalPrice = 0.0;

    public CartAdapter(Context context, List<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartModel cartModel = cartModelList.get(position);

        holder.nameTv.setText("" + cartModel.getProductName());
        holder.unitPriceTv.setText("" + cartModel.getProductPrice());
        holder.quantityTv.setText("" + cartModel.getProductQuantity());
        double totalPrice = Double.parseDouble(cartModel.getTotalPrice());
        holder.totalPriceTv.setText("" + totalPrice);

        inTotalPrice = inTotalPrice + totalPrice;

        Intent intent = new Intent("MY_IN_TOTAL_PRICE");
        intent.putExtra("TOTAL_ITEMS", cartModelList.size());
        intent.putExtra("IN_TOTAL_PRICE", inTotalPrice);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public int getItemCount() {
        return cartModelList == null ? 0 : cartModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTv, unitPriceTv, quantityTv, totalPriceTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.productNameTv);
            unitPriceTv = itemView.findViewById(R.id.unitPriceTv);
            quantityTv = itemView.findViewById(R.id.quantityTv);
            totalPriceTv = itemView.findViewById(R.id.totalPriceTv);
        }
    }
}