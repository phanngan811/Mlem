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

import com.example.mlem.Adapter.RecipeRVAdapter;
import com.example.mlem.ViewModel.RecipeSearchResultVM;
import com.example.mlem.databinding.FragmentRecipeSearchResultBinding;

public class RecipeSearchResultFragment extends Fragment {

    private RecipeSearchResultVM mViewModel;
    private FragmentRecipeSearchResultBinding mBinding;
    private View mView;
    private RecipeRVAdapter mRVAdapter;
    private SearchResultActivity mSearchResultActivity;

    public static RecipeSearchResultFragment newInstance() {
        return new RecipeSearchResultFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentRecipeSearchResultBinding.inflate(inflater, container, false);
        mView = mBinding.getRoot();
        mSearchResultActivity = (SearchResultActivity) getActivity();

        initAdapters();
        initObservers();

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
        mSearchResultActivity.getSearchQuery().observe(getViewLifecycleOwner(), s -> {
            mViewModel.search(s, mSearchResultActivity.getSearchByType().getValue());
        });
        mSearchResultActivity.getSearchByType().observe(getViewLifecycleOwner(), t -> {
            mViewModel.search(mSearchResultActivity.getSearchQuery().getValue(), t);
        });
    }

    private void initAdapters() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mView.getContext(), 2);
        mBinding.rvResult.setLayoutManager(gridLayoutManager);
        mRVAdapter = new RecipeRVAdapter(mView.getContext());
        mBinding.rvResult.setAdapter(mRVAdapter);
    }
}