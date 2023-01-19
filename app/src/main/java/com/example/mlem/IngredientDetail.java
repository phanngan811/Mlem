package com.example.mlem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mlem.ViewModel.IngredientVM;
import com.squareup.picasso.Picasso;

public class IngredientDetail extends AppCompatActivity {

    IngredientVM ingredientVM;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_detail);

        TextView ingredientName = findViewById(R.id.ingredient_name);
        TextView ingredientPrice = findViewById(R.id.ingredient_price);
        TextView ingredientDescription = findViewById(R.id.ingredient_description);
        ImageView ingredientImg = findViewById(R.id.ingredient_img);

        Button plusBtn = findViewById(R.id.plusBtn);
        TextView numberText = findViewById(R.id.numberText);
        Button minusBtn = findViewById(R.id.minusBtn);
        Button addBtn = findViewById(R.id.addBtn);

        Intent intent = getIntent();
        String ingredientId = intent.getStringExtra("ingredientId");

        ingredientVM = new ViewModelProvider(this).get(IngredientVM.class);
        ingredientVM.setId(ingredientId);
        ingredientVM.getOne();

        ingredientVM.getIngredient().observe(this, ingredient -> {
            ingredientName.setText(ingredient.getName());
            //ingredientDescription.setText(ingredient.getDescription());
            ingredientPrice.setText(String.valueOf(ingredient.getPrice()));
            if(ingredient.getImageUrl() != null){
                Picasso.get().load(ingredient.getImageUrl()).into(ingredientImg);
            }
        });

        //change amount of ingredients to add to cart
//        public void addAmount() {
//
//        }
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