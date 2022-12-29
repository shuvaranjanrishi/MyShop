package com.therishideveloper.myshop.adapters;

/*
    Created by Shuva Ranjan Rishi on 12/29/2022
*/

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.therishideveloper.myshop.R;
import com.therishideveloper.myshop.models.ProductCategory;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final Context context;
    private final List<ProductCategory> productCategoryList;

    public CategoryAdapter(Context context, List<ProductCategory> productCategoryList) {
        this.context = context;
        this.productCategoryList = productCategoryList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        ProductCategory productCategory = productCategoryList.get(position);

        holder.nameTv.setText(""+productCategory.getName());
        Picasso.get()
                .load(productCategory.getImageUrl())
                .placeholder(R.drawable.fruits)
                .into(holder.categoryIv);
    }

    @Override
    public int getItemCount() {
        return productCategoryList == null ? 0 : productCategoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView categoryIv;
        public TextView nameTv,typeTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryIv = itemView.findViewById(R.id.categoryIv);
            nameTv = itemView.findViewById(R.id.nameTv);
        }
    }
}
