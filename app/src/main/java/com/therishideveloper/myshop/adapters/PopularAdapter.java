package com.therishideveloper.myshop.adapters;

/*
    Created by Shuva Ranjan Rishi on 12/29/2022
*/

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.therishideveloper.myshop.R;
import com.therishideveloper.myshop.activities.ViewAllActivity;
import com.therishideveloper.myshop.models.PopularModel;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private final Context context;
    private final List<PopularModel> popularProductList;

    public PopularAdapter(Context context, List<PopularModel> popularProductList) {
        this.context = context;
        this.popularProductList = popularProductList;
    }

    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_popular, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {
        PopularModel popularProduct = popularProductList.get(position);

        holder.productNameTv.setText(""+popularProduct.getProductName());
        holder.descriptionTv.setText(""+popularProduct.getDescription());
        holder.ratingTv.setText(""+popularProduct.getRating());
        holder.discountTv.setText("Discount "+popularProduct.getDiscount()+"% Off");
        Picasso.get()
                .load(popularProduct.getImageUrl())
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(holder.productIv);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ViewAllActivity.class);
            intent.putExtra("TYPE",popularProduct.getType());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return popularProductList == null ? 0 : popularProductList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView productIv;
        public TextView productNameTv, descriptionTv, ratingTv, discountTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productIv = itemView.findViewById(R.id.productIv);
            productNameTv = itemView.findViewById(R.id.productNameTv);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);
            ratingTv = itemView.findViewById(R.id.ratingTv);
            discountTv = itemView.findViewById(R.id.discountTv);
        }
    }
}
