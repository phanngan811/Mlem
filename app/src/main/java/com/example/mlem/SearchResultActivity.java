package com.example.mlem;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.mlem.Enum.SearchType;
import com.example.mlem.ViewModel.SearchResultViewModel;
import com.example.mlem.databinding.ActivitySearchResultBinding;

public class SearchResultActivity extends AppCompatActivity {
    private ActivitySearchResultBinding mBinding;
    private SearchResultViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySearchResultBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        replaceFragment(new IngredientSearchResultFragment());

        mViewModel = new ViewModelProvider(this).get(SearchResultViewModel.class);

        initListeners();
        initObservers();
    }

    private void initListeners() {
        mBinding.btnTypeBlog.setOnClickListener(view -> {
            mViewModel.changeSearchType(SearchType.BLOG);
        });
        mBinding.btnTypeIngredient.setOnClickListener(view -> {
            mViewModel.changeSearchType(SearchType.INGREDIENT);
        });
        mBinding.btnTypeRecipe.setOnClickListener(view -> {
            mViewModel.changeSearchType(SearchType.RECIPE);
        });
    }

    private void initObservers() {
        int primary = ContextCompat.getColor(this, R.color.primary);
        int neutral = ContextCompat.getColor(this, R.color.neutral_400);

        mViewModel.getSearchType().observe(this, searchType -> {
            if (searchType == SearchType.RECIPE) {
                mBinding.btnTypeRecipe.setBackgroundColor(primary);
                mBinding.btnTypeBlog.setBackgroundColor(neutral);
                mBinding.btnTypeIngredient.setBackgroundColor(neutral);
            } else if (searchType == SearchType.BLOG) {
                mBinding.btnTypeRecipe.setBackgroundColor(neutral);
                mBinding.btnTypeBlog.setBackgroundColor(primary);
                mBinding.btnTypeIngredient.setBackgroundColor(neutral);
            } else {
                replaceFragment(new IngredientSearchResultFragment());
                mBinding.btnTypeRecipe.setBackgroundColor(neutral);
                mBinding.btnTypeBlog.setBackgroundColor(neutral);
                mBinding.btnTypeIngredient.setBackgroundColor(primary);
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}