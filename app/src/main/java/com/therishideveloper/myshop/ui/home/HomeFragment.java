package com.therishideveloper.myshop.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.therishideveloper.myshop.adapters.CategoryHomeAdapter;
import com.therishideveloper.myshop.adapters.PopularAdapter;
import com.therishideveloper.myshop.adapters.RecommendedAdapter;
import com.therishideveloper.myshop.databinding.FragmentHomeBinding;
import com.therishideveloper.myshop.models.PopularModel;
import com.therishideveloper.myshop.models.CategoryHome;
import com.therishideveloper.myshop.models.RecommendedModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<PopularModel> popularProductList;
    private PopularAdapter popularAdapter;
    private List<CategoryHome> productCategoryList;
    private CategoryHomeAdapter categoryAdapter;
    private List<RecommendedModel> recommendedModelList;
    private RecommendedAdapter recommendedAdapter;
    private FirebaseFirestore db;

    @SuppressLint("NotifyDataSetChanged")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.progressBar.setVisibility(View.VISIBLE);
        binding.scrollView.setVisibility(View.GONE);

        db = FirebaseFirestore.getInstance();
        popularProductList = new ArrayList<>();
        productCategoryList = new ArrayList<>();
        recommendedModelList = new ArrayList<>();
        popularAdapter = new PopularAdapter(getActivity(),popularProductList);
        binding.popularProductsRv.setAdapter(popularAdapter);
        categoryAdapter = new CategoryHomeAdapter(getActivity(),productCategoryList);
        binding.categoryHomeRv.setAdapter(categoryAdapter);
        recommendedAdapter = new RecommendedAdapter(getActivity(),recommendedModelList);
        binding.recommendProductsRv.setAdapter(recommendedAdapter);

        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot snapshot : task.getResult()){
                            PopularModel popularModel = snapshot.toObject(PopularModel.class);
                            popularProductList.add(popularModel);
                            popularAdapter.notifyDataSetChanged();
                        }
                    }else {
                        Toast.makeText(getActivity(), "Error..."+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                    binding.progressBar.setVisibility(View.GONE);
                    binding.scrollView.setVisibility(View.VISIBLE);
                });

        db.collection("ProductCategory")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot snapshot : task.getResult()){
                            CategoryHome productCategory = snapshot.toObject(CategoryHome.class);
                            productCategoryList.add(productCategory);
                            categoryAdapter.notifyDataSetChanged();
                        }
                    }else {
                        Toast.makeText(getActivity(), "Error..."+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });

        db.collection("RecommendedProducts")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot snapshot : task.getResult()){
                            RecommendedModel recommendedModel = snapshot.toObject(RecommendedModel.class);
                            recommendedModelList.add(recommendedModel);
                            recommendedAdapter.notifyDataSetChanged();
                        }
                    }else {
                        Toast.makeText(getActivity(), "Error..."+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}