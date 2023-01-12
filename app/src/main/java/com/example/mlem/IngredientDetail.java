package com.example.mlem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class IngredientDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_detail);

        TextView ingredientName = findViewById(R.id.ingredient_name);
        TextView ingredientPrice = findViewById(R.id.ingredient_price);
        TextView ingredientDescription = findViewById(R.id.ingredient_description);

        Button plusBtn = findViewById(R.id.plusBtn);
        TextView numberText = findViewById(R.id.numberText);
        Button minusBtn = findViewById(R.id.minusBtn);
        Button addBtn = findViewById(R.id.addBtn);
    }
}