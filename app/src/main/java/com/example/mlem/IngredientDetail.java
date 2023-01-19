package com.example.mlem;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mlem.ViewModel.IngredientVM;
import com.example.mlem.databinding.ActivityIngredientDetailBinding;
import com.squareup.picasso.Picasso;

public class IngredientDetail extends AppCompatActivity {

    IngredientVM ingredientVM;
    ActivityIngredientDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityIngredientDetailBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        Intent intent = getIntent();
        String ingredientId = intent.getStringExtra("ingredientId");

        ingredientVM = new ViewModelProvider(this).get(IngredientVM.class);

        ingredientVM.setId(ingredientId);

        initObservers();
        initListeners();
    }

    private void initObservers() {
        ingredientVM.getId().observe(this, id -> {
            ingredientVM.getOne();
        });
        ingredientVM.getIngredient().observe(this, ingredient -> {
            mBinding.ingredientName.setText(ingredient.getName());
            mBinding.ingredientDescription.setText(ingredient.getDescription());
            mBinding.ingredientPrice.setText(String.format("%s %s", ingredient.getPrice(), ingredient.getUnit()));
            if (ingredient.getImageUrl() != null) {
                Picasso.get().load(ingredient.getImageUrl()).into(mBinding.ingredientImg);
            }
        });
    }

    private void initListeners() {
        mBinding.minusBtn.setOnClickListener(v -> {
            int curNum = Integer.parseInt(mBinding.numberText.getText().toString());
            if (curNum <= 1) return;
            mBinding.numberText.setText(String.valueOf(curNum - 1));
        });
        mBinding.plusBtn.setOnClickListener(v -> {
            int curNum = Integer.parseInt(mBinding.numberText.getText().toString());
            mBinding.numberText.setText(String.valueOf(curNum + 1));
        });
        mBinding.addBtn.setOnClickListener(v -> {
            int curNum = Integer.parseInt(mBinding.numberText.getText().toString());
            ingredientVM.addCart(curNum);
            Intent intent = new Intent(IngredientDetail.this, OrderSummaryActivity.class);
            startActivity(intent);
        });
    }
}