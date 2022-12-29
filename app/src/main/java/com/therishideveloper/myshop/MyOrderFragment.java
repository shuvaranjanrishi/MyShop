package com.therishideveloper.myshop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.therishideveloper.myshop.databinding.FragmentMyCartBinding;
import com.therishideveloper.myshop.databinding.FragmentMyOrderBinding;

public class MyOrderFragment extends Fragment {

    private FragmentMyOrderBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyOrderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}