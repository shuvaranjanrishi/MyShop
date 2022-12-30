package com.therishideveloper.myshop.ui.category;

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
import com.therishideveloper.myshop.adapters.CategoryNavAdapter;
import com.therishideveloper.myshop.adapters.RecommendedAdapter;
import com.therishideveloper.myshop.databinding.FragmentCategoryBinding;
import com.therishideveloper.myshop.models.CategoryHome;
import com.therishideveloper.myshop.models.CategoryNav;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding binding;
    private List<CategoryNav> categoryNavList;
    private CategoryNavAdapter categoryNavAdapter;
    private FirebaseFirestore db;

    @SuppressLint("NotifyDataSetChanged")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);

        binding.progressBar.setVisibility(View.VISIBLE);
        binding.categoryNavRv.setVisibility(View.GONE);

        db = FirebaseFirestore.getInstance();
        categoryNavList = new ArrayList<>();
        categoryNavAdapter = new CategoryNavAdapter(getActivity(),categoryNavList);
        binding.categoryNavRv.setAdapter(categoryNavAdapter);

        db.collection("CategoryNav")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot snapshot : task.getResult()){
                            CategoryNav categoryNav = snapshot.toObject(CategoryNav.class);
                            categoryNavList.add(categoryNav);
                            categoryNavAdapter.notifyDataSetChanged();
                        }
                    }else {
                        Toast.makeText(getActivity(), "Error..."+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                    binding.progressBar.setVisibility(View.GONE);
                    binding.categoryNavRv.setVisibility(View.VISIBLE);
                });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}