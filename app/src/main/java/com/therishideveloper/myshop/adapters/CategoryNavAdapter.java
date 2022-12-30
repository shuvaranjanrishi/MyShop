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
import com.therishideveloper.myshop.models.CategoryNav;

import java.util.List;

public class CategoryNavAdapter extends RecyclerView.Adapter<CategoryNavAdapter.ViewHolder> {

    private final Context context;
    private final List<CategoryNav> categoryNavList;

    public CategoryNavAdapter(Context context, List<CategoryNav> categoryNavList) {
        this.context = context;
        this.categoryNavList = categoryNavList;
    }

    @NonNull
    @Override
    public CategoryNavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category_nav, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryNavAdapter.ViewHolder holder, int position) {
        CategoryNav categoryNav = categoryNavList.get(position);

        holder.nameTv.setText("" + categoryNav.getName());
        holder.descriptionTv.setText("" + categoryNav.getDescription());
        holder.discountTv.setText("" + categoryNav.getDiscount()+"% OFF");
        Picasso.get()
                .load(categoryNav.getImageUrl())
                .placeholder(R.drawable.fruits)
                .into(holder.categoryIv);
    }

    @Override
    public int getItemCount() {
        return categoryNavList == null ? 0 : categoryNavList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView categoryIv;
        public TextView nameTv, descriptionTv, discountTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryIv = itemView.findViewById(R.id.categoryIv);
            nameTv = itemView.findViewById(R.id.nameTv);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);
            discountTv = itemView.findViewById(R.id.discountTv);
        }
    }
}
