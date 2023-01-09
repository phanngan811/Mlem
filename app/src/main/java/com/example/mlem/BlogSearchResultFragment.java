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

import com.example.mlem.Adapter.BlogRVAdapter;
import com.example.mlem.ViewModel.BlogSearchResultVM;
import com.example.mlem.databinding.FragmentBlogSearchResultBinding;

public class BlogSearchResultFragment extends Fragment {

    private BlogSearchResultVM mViewModel;
    private FragmentBlogSearchResultBinding mBinding;
    private View mView;
    private BlogRVAdapter mRVAdapter;
    private SearchResultActivity mSearchResultActivity;

    public static BlogSearchResultFragment newInstance() {
        return new BlogSearchResultFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentBlogSearchResultBinding.inflate(inflater, container, false);
        mView = mBinding.getRoot();
        mSearchResultActivity = (SearchResultActivity) getActivity();

        initAdapters();
        initObservers();

        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BlogSearchResultVM.class);
    }

    private void initObservers() {
        mViewModel.getResult().observe(getViewLifecycleOwner(), result -> {
            mRVAdapter.setBlogs(result);
        });
        mSearchResultActivity.getSearchQuery().observe(getViewLifecycleOwner(), s -> {
            mViewModel.search(s);
        });
    }

    private void initAdapters() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext());
        mBinding.rvResult.setLayoutManager(linearLayoutManager);
        mRVAdapter = new BlogRVAdapter();
        mBinding.rvResult.setAdapter(mRVAdapter);
    }
}