package com.example.mlem;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
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
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityOrderSummaryBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Order Summary");

        mViewModel = new ViewModelProvider(this).get(OrderSummaryVM.class);
        mViewModel.getCart();

        initListeners();
        initAdapters();
        initObservers();
    }

    private void initListeners() {
        mBinding.btnConfirm.setOnClickListener(v -> {
            Intent intent = new Intent(OrderSummaryActivity.this, OrderCompleteActivity.class);
            startActivity(intent);
        });
    }

    private void initObservers() {
        mViewModel.getResult().observe(this, result -> {
            mRVAdapter.setCart(result);
        });
        mViewModel.getTotal().observe(this, total -> {
            mBinding.txtPrice.setText(String.valueOf(total));
        });
    }

    private void initAdapters() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mBinding.rvCart.setLayoutManager(linearLayoutManager);
        mRVAdapter = new CartRVAdapter();
        mBinding.rvCart.setAdapter(mRVAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}