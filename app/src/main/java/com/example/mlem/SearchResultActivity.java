package com.example.mlem;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.NonNull;
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

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.searchBar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search...");

        return true;
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
                replaceFragment(new RecipeSearchResultFragment());
                mBinding.btnTypeRecipe.setBackgroundColor(primary);
                mBinding.btnTypeBlog.setBackgroundColor(neutral);
                mBinding.btnTypeIngredient.setBackgroundColor(neutral);
            } else if (searchType == SearchType.BLOG) {
                replaceFragment(new BlogSearchResultFragment());
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