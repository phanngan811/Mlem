package com.example.mlem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mlem.Adapter.RecipeIngredientRVAdapter;
import com.example.mlem.Adapter.RecipeStepRVAdapter;
import com.example.mlem.ViewModel.RecipeDetailVM;
import com.example.mlem.databinding.ActivityRecipeDetailBinding;

public class RecipeDetail extends AppCompatActivity {
    private RecipeDetailVM mViewModel;
    private ActivityRecipeDetailBinding mBinding;
    private RecipeIngredientRVAdapter mIngredientRVAdapter;
    private RecipeStepRVAdapter mStepRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRecipeDetailBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        String id = intent.getStringExtra("recipeId");

        mViewModel = new ViewModelProvider(this).get(RecipeDetailVM.class);

        mViewModel.setId("IJ1gDWZZ0hT4Sz8OEapH");
        mViewModel.getOne();

        initListeners();
        initAdapters();
        initObservers();
    }

    private void initListeners() {
        mBinding.btnAddToCart.setOnClickListener(v -> {
            Intent intent = new Intent(RecipeDetail.this, OrderSummaryActivity.class);
            startActivity(intent);
        });
    }

    private void initObservers() {
        mViewModel.getRecipe().observe(this, recipe -> {
            mBinding.tvTitle.setText(recipe.getName());
            if (recipe.getIngredients() != null) {
                mIngredientRVAdapter.setIngredients(recipe.getIngredients());
            }
            if (recipe.getSteps() != null) {
                mStepRVAdapter.setSteps(recipe.getSteps());
            }
        });
    }

    private void initAdapters() {
        LinearLayoutManager ingredientLayoutManger = new LinearLayoutManager(this);
        mBinding.rvIngredients.setLayoutManager(ingredientLayoutManger);
        mIngredientRVAdapter = new RecipeIngredientRVAdapter();
        mBinding.rvIngredients.setAdapter(mIngredientRVAdapter);

        LinearLayoutManager stepLayoutManager = new LinearLayoutManager(this);
        mBinding.rvDirections.setLayoutManager(stepLayoutManager);
        mStepRVAdapter = new RecipeStepRVAdapter();
        mBinding.rvDirections.setAdapter(mStepRVAdapter);
    }
}