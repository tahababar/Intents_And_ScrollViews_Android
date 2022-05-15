package com.tahababar.csfeature;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tahababar.csfeature.databinding.ActivityBiographyBinding;
import com.tahababar.csfeature.databinding.ActivityHomeBinding;

public class BiographyActivity extends AppCompatActivity {

    private ActivityBiographyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBiographyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}