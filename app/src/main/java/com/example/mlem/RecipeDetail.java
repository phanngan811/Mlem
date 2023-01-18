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

        // get id from intent from the recycler view adapter
        Intent intent = getIntent();
        String recipeId = intent.getStringExtra("recipeId");

        mViewModel = new ViewModelProvider(this).get(RecipeDetailVM.class);

        mViewModel.setId(recipeId);
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
            mBinding.tvTime.setText(recipe.getDuration());
            mBinding.tvDifficulty.setText(recipe.getDifficulty());
            mBinding.tvRating.setText(String.valueOf(recipe.getRating()));
            if (recipe.getCartItems() != null) {
                mIngredientRVAdapter.setCartItems(recipe.getCartItems());
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