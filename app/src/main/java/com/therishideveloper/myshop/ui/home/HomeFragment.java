package com.therishideveloper.myshop.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.therishideveloper.myshop.adapters.PopularAdapter;
import com.therishideveloper.myshop.databinding.FragmentHomeBinding;
import com.therishideveloper.myshop.models.PopularModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<PopularModel> popularProductList;
    private PopularAdapter popularAdapter;
    private FirebaseFirestore db;

    @SuppressLint("NotifyDataSetChanged")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        db = FirebaseFirestore.getInstance();
        popularProductList = new ArrayList<>();
        popularAdapter = new PopularAdapter(getActivity(),popularProductList);
        binding.popularProductsRv.setAdapter(popularAdapter);

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
                });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}