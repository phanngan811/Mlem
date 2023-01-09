package com.example.mlem;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mlem.Adapter.IngredientRVAdapter;
import com.example.mlem.Enum.SearchType;
import com.example.mlem.ViewModel.IngredientSearchResultVM;
import com.example.mlem.ViewModel.SearchResultViewModel;
import com.example.mlem.databinding.FragmentIngredientSearchResultBinding;

public class IngredientSearchResultFragment extends Fragment {

    private IngredientSearchResultVM mViewModel;
    private FragmentIngredientSearchResultBinding mBinding;
    private View mView;
    private IngredientRVAdapter mRVAdapter;

    public static IngredientSearchResultFragment newInstance() {
        return new IngredientSearchResultFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentIngredientSearchResultBinding.inflate(inflater, container, false);
        mView = mBinding.getRoot();

        initAdapters();
        initObservers();

        mViewModel.search("egg");

        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(IngredientSearchResultVM.class);
    }

    private void initObservers() {
        mViewModel.getResult().observe(getViewLifecycleOwner(), result -> {
            mRVAdapter.setIngredients(result);
        });
    }

    private void initAdapters() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext());
        mBinding.rvResult.setLayoutManager(linearLayoutManager);
        mRVAdapter = new IngredientRVAdapter();
        mBinding.rvResult.setAdapter(mRVAdapter);
    }
}