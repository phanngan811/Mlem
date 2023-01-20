package com.example.mlem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mlem.Adapter.CartRVAdapter;
import com.example.mlem.ViewModel.OrderSummaryVM;
import com.example.mlem.databinding.ActivityOrderSummaryBinding;

public class OrderSummaryActivity extends AppCompatActivity {
    OrderSummaryVM mViewModel;
    ActivityOrderSummaryBinding mBinding;
    CartRVAdapter mRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityOrderSummaryBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);


        mViewModel = new ViewModelProvider(this).get(OrderSummaryVM.class);
        mViewModel.getCart();

        initListeners();
        initAdapters();
        initObservers();
    }

    private void initListeners() {
        mBinding.btnConfirm.setOnClickListener(v -> {
            mViewModel.removeAllCart();
            Intent intent = new Intent(OrderSummaryActivity.this, OrderCompleteActivity.class);
            startActivity(intent);
        });
    }

    private void initObservers() {
        mViewModel.getResult().observe(this, result -> {
            mRVAdapter.setCart(result);
        });
        mViewModel.getTotal().observe(this, total -> {
            mBinding.txtPrice.setText(total);
        });
    }

    private void initAdapters() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mBinding.rvCart.setLayoutManager(linearLayoutManager);
        mRVAdapter = new CartRVAdapter();
        mBinding.rvCart.setAdapter(mRVAdapter);
    }
}