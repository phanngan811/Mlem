package com.example.mlem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mlem.Adapter.IngredientRVAdapter;
import com.example.mlem.ViewModel.IngredientSearchResultVM;
import com.example.mlem.databinding.FragmentIngredientSearchResultBinding;

public class IngredientSearchResultFragment extends Fragment {

    private IngredientSearchResultVM mViewModel;
    private FragmentIngredientSearchResultBinding mBinding;
    private View mView;
    private IngredientRVAdapter mRVAdapter;
    private SearchResultActivity mSearchResultActivity;

    public static IngredientSearchResultFragment newInstance() {
        return new IngredientSearchResultFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentIngredientSearchResultBinding.inflate(inflater, container, false);
        mView = mBinding.getRoot();
        mSearchResultActivity = (SearchResultActivity) getActivity();

        initAdapters();
        initObservers();

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
        mSearchResultActivity.getSearchQuery().observe(getViewLifecycleOwner(), s -> {
            mViewModel.search(s);
        });
    }

    private void initAdapters() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mView.getContext(), 2);
        mBinding.rvResult.setLayoutManager(gridLayoutManager);
        mRVAdapter = new IngredientRVAdapter();
        mBinding.rvResult.setAdapter(mRVAdapter);
    }
}