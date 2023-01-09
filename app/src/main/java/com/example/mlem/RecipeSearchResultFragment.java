package com.example.mlem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mlem.Adapter.RecipeRVAdapter;
import com.example.mlem.ViewModel.RecipeSearchResultVM;
import com.example.mlem.databinding.FragmentRecipeSearchResultBinding;

public class RecipeSearchResultFragment extends Fragment {

    private RecipeSearchResultVM mViewModel;
    private FragmentRecipeSearchResultBinding mBinding;
    private View mView;
    private RecipeRVAdapter mRVAdapter;

    public static RecipeSearchResultFragment newInstance() {
        return new RecipeSearchResultFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentRecipeSearchResultBinding.inflate(inflater, container, false);
        mView = mBinding.getRoot();

        initAdapters();
        initObservers();

        mViewModel.search("recipe 1");

        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RecipeSearchResultVM.class);
    }

    private void initObservers() {
        mViewModel.getResult().observe(getViewLifecycleOwner(), result -> {
            mRVAdapter.setRecipes(result);
        });
    }

    private void initAdapters() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext());
        mBinding.rvResult.setLayoutManager(linearLayoutManager);
        mRVAdapter = new RecipeRVAdapter();
        mBinding.rvResult.setAdapter(mRVAdapter);
    }
}