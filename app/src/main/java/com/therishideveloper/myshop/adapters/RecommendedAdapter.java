package com.therishideveloper.myshop.adapters;

/*
    Created by Shuva Ranjan Rishi on 12/29/2022
*/

import android.annotation.SuppressLint;
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
import com.therishideveloper.myshop.models.PopularModel;
import com.therishideveloper.myshop.models.RecommendedModel;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {

    private final Context context;
    private final List<RecommendedModel> recommendedModelList;

    public RecommendedAdapter(Context context, List<RecommendedModel> recommendedModelList) {
        this.context = context;
        this.recommendedModelList = recommendedModelList;
    }

    @NonNull
    @Override
    public RecommendedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recommend, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecommendedAdapter.ViewHolder holder, int position) {
        RecommendedModel recommendedModel = recommendedModelList.get(position);

        holder.productNameTv.setText(""+recommendedModel.getProductName());
        holder.descriptionTv.setText(""+recommendedModel.getDescription());
        holder.ratingTv.setText(""+recommendedModel.getRating());
        holder.priceTv.setText("$ "+recommendedModel.getPrice());
        Picasso.get()
                .load(recommendedModel.getImageUrl())
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(holder.productIv);
    }

    @Override
    public int getItemCount() {
        return recommendedModelList == null ? 0 : recommendedModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView productIv;
        public TextView productNameTv, descriptionTv, ratingTv, priceTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productIv = itemView.findViewById(R.id.productIv);
            productNameTv = itemView.findViewById(R.id.productNameTv);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);
            ratingTv = itemView.findViewById(R.id.ratingTv);
            priceTv = itemView.findViewById(R.id.priceTv);
        }
    }
}
