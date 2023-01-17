package com.example.mlem;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.Adapter.RecipeRVAdapter;
import com.example.mlem.Adapter.TagChipRVAdapter;
import com.example.mlem.Model.Recipe;
import com.example.mlem.ViewModel.MakeMeADishVM;
import com.example.mlem.databinding.ActivityMakeMeAdishBinding;

public class MakeMeADishActivity extends AppCompatActivity {
    ActivityMakeMeAdishBinding mBinding;
    TagChipRVAdapter mRVAdapter;
    RecipeRVAdapter mRecipeRVAdapter;
    MakeMeADishVM mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMakeMeAdishBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        mViewModel = new ViewModelProvider(this).get(MakeMeADishVM.class);

        initAdapters();
        initObservers();

        mBinding.btnConfirm.setOnClickListener(v -> {
            String tmp = mBinding.svSearchBar.getText().toString().trim();
            if (tmp.equals("")) return;
            mViewModel.setChips(mRVAdapter.getChips());
            mViewModel.addChip(tmp);
            mBinding.svSearchBar.setText("");
            mViewModel.searchRecipes();
        });
    }

    private void initObservers() {
        mViewModel.getChips().observe(this, chips -> {
            mRVAdapter.setChips(chips);
            mViewModel.searchRecipes();
        });
        mViewModel.getRecipes().observe(this, recipes -> {
            mRecipeRVAdapter.setRecipes(recipes);
        });
    }

    private void initAdapters() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mBinding.rvChips.setLayoutManager(linearLayoutManager);
        mRVAdapter = new TagChipRVAdapter();
        mBinding.rvChips.setAdapter(mRVAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mBinding.rvRecipes.setLayoutManager(gridLayoutManager);
        mRecipeRVAdapter = new RecipeRVAdapter();
        mBinding.rvRecipes.setAdapter(mRecipeRVAdapter);
    }
}