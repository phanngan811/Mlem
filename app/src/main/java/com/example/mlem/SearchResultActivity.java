package com.example.mlem;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mlem.Adapter.IngredientRVAdapter;
import com.example.mlem.Enum.SearchType;
import com.example.mlem.ViewModel.SearchResultViewModel;
import com.example.mlem.databinding.ActivitySearchResultBinding;

public class SearchResultActivity extends AppCompatActivity {
    private ActivitySearchResultBinding binding;
    private SearchResultViewModel viewModel;
    private IngredientRVAdapter ingredientRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchResultBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(SearchResultViewModel.class);

        initListeners();
        initObservers();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvResultIngredient.setLayoutManager(linearLayoutManager);
        ingredientRVAdapter = new IngredientRVAdapter();
        binding.rvResultIngredient.setAdapter(ingredientRVAdapter);

        viewModel.search("egg");
    }

    private void initListeners() {
        binding.btnTypeBlog.setOnClickListener(view -> {
            viewModel.changeSearchType(SearchType.BLOG);
        });
        binding.btnTypeIngredient.setOnClickListener(view -> {
            viewModel.changeSearchType(SearchType.INGREDIENT);
        });
        binding.btnTypeRecipe.setOnClickListener(view -> {
            viewModel.changeSearchType(SearchType.RECIPE);
        });
    }

    private void initObservers() {
        int primary = ContextCompat.getColor(this, R.color.primary);
        int neutral = ContextCompat.getColor(this, R.color.neutral_400);

        viewModel.getSearchType().observe(this, searchType -> {
            if (searchType == SearchType.RECIPE) {
                binding.rvResultBlog.setVisibility(View.INVISIBLE);
                binding.rvResultRecipe.setVisibility(View.VISIBLE);
                binding.rvResultIngredient.setVisibility(View.INVISIBLE);

                binding.btnTypeRecipe.setBackgroundColor(primary);
                binding.btnTypeBlog.setBackgroundColor(neutral);
                binding.btnTypeIngredient.setBackgroundColor(neutral);
            } else if (searchType == SearchType.BLOG) {
                binding.rvResultBlog.setVisibility(View.VISIBLE);
                binding.rvResultRecipe.setVisibility(View.INVISIBLE);
                binding.rvResultIngredient.setVisibility(View.INVISIBLE);

                binding.btnTypeRecipe.setBackgroundColor(neutral);
                binding.btnTypeBlog.setBackgroundColor(primary);
                binding.btnTypeIngredient.setBackgroundColor(neutral);
            } else {
                binding.rvResultBlog.setVisibility(View.INVISIBLE);
                binding.rvResultRecipe.setVisibility(View.INVISIBLE);
                binding.rvResultIngredient.setVisibility(View.VISIBLE);

                binding.btnTypeRecipe.setBackgroundColor(neutral);
                binding.btnTypeBlog.setBackgroundColor(neutral);
                binding.btnTypeIngredient.setBackgroundColor(primary);
            }
        });

        viewModel.getResult().observe(this, result -> {
            ingredientRVAdapter.setIngredients(result);
        });
    }
}