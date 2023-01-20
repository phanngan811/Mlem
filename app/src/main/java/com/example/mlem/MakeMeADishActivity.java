package com.example.mlem;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.Adapter.MMADRecipeRVAdapter;
import com.example.mlem.Adapter.TagChipRVAdapter;
import com.example.mlem.ViewModel.MakeMeADishVM;
import com.example.mlem.databinding.ActivityMakeMeAdishBinding;

import java.util.Objects;

public class MakeMeADishActivity extends AppCompatActivity {
    ActivityMakeMeAdishBinding mBinding;
    TagChipRVAdapter mChipRVAdapter;
    MMADRecipeRVAdapter mRecipeRVAdapter;
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
        initListeners();
    }

    private void initObservers() {
        mChipRVAdapter.getChips().observe(this, chips -> {
            mViewModel.searchRecipes(chips);
        });
        mViewModel.getRecipes().observe(this, recipes -> {
            mRecipeRVAdapter.setRecipes(recipes);
        });
    }

    private void initListeners() {
        mBinding.btnConfirm.setOnClickListener(v -> {
            String tmp = Objects.requireNonNull(mBinding.svSearchBar.getText()).toString().trim();
            if (tmp.equals("")) return;
            String[] tmpList = tmp.split(" ");
            for (String c : tmpList) {
                mChipRVAdapter.addChip(c);
            }
            mBinding.svSearchBar.setText("");
        });
    }

    private void initAdapters() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mBinding.rvChips.setLayoutManager(linearLayoutManager);
        mChipRVAdapter = new TagChipRVAdapter();
        mBinding.rvChips.setAdapter(mChipRVAdapter);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        mBinding.rvRecipes.setLayoutManager(linearLayoutManager1);
        mRecipeRVAdapter = new MMADRecipeRVAdapter(this);
        mBinding.rvRecipes.setAdapter(mRecipeRVAdapter);
    }
}