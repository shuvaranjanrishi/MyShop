package com.therishideveloper.myshop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.therishideveloper.myshop.databinding.FragmentMyOrderBinding;
import com.therishideveloper.myshop.databinding.FragmentNewProductBinding;

public class NewProductFragment extends Fragment {

    private FragmentNewProductBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}