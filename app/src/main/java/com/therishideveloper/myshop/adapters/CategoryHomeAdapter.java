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
import com.therishideveloper.myshop.models.CategoryHome;

import java.util.List;

public class CategoryHomeAdapter extends RecyclerView.Adapter<CategoryHomeAdapter.ViewHolder> {

    private final Context context;
    private final List<CategoryHome> productCategoryList;

    public CategoryHomeAdapter(Context context, List<CategoryHome> productCategoryList) {
        this.context = context;
        this.productCategoryList = productCategoryList;
    }

    @NonNull
    @Override
    public CategoryHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHomeAdapter.ViewHolder holder, int position) {
        CategoryHome productCategory = productCategoryList.get(position);

        holder.nameTv.setText(""+productCategory.getName());
        Picasso.get()
                .load(productCategory.getImageUrl())
                .placeholder(R.drawable.fruits)
                .into(holder.categoryIv);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ViewAllActivity.class);
            intent.putExtra("TYPE",productCategory.getType());
            context.startActivity(intent);
        });
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
