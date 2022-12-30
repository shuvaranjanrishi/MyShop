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
import com.therishideveloper.myshop.models.CategoryHome;
import com.therishideveloper.myshop.models.ViewAllModel;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

    private final Context context;
    private final List<ViewAllModel> viewAllModelList;

    public ViewAllAdapter(Context context, List<ViewAllModel> viewAllModelList) {
        this.context = context;
        this.viewAllModelList = viewAllModelList;
    }

    @NonNull
    @Override
    public ViewAllAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view_all, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllAdapter.ViewHolder holder, int position) {
        ViewAllModel viewAllModel = viewAllModelList.get(position);

        holder.nameTv.setText(""+viewAllModel.getName());
        holder.descriptionTv.setText(""+viewAllModel.getDescription());
        holder.ratingTv.setText("$ "+viewAllModel.getRating());
        holder.priceTv.setText("$ "+viewAllModel.getPrice()+"/kg");
        if(viewAllModel.getType().equals("Milk")) holder.priceTv.setText("$ "+viewAllModel.getPrice()+"/liter");
        if(viewAllModel.getType().equals("Eggs")) holder.priceTv.setText("$ "+viewAllModel.getPrice()+"/dozen");
        if(viewAllModel.getType().equals("Drinks")) holder.priceTv.setText("$ "+viewAllModel.getPrice()+"/bottle");

        Picasso.get()
                .load(viewAllModel.getImageUrl())
                .placeholder(R.drawable.fruits)
                .into(holder.productIv);
    }

    @Override
    public int getItemCount() {
        return viewAllModelList == null ? 0 : viewAllModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView productIv;
        public TextView nameTv,descriptionTv,ratingTv,priceTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productIv = itemView.findViewById(R.id.productIv);
            nameTv = itemView.findViewById(R.id.nameTv);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);
            ratingTv = itemView.findViewById(R.id.ratingTv);
            priceTv = itemView.findViewById(R.id.priceTv);
        }
    }
}
