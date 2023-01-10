package com.example.mlem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class RecipeDetail extends AppCompatActivity {
    ListView lvIngredient;
    String[] ingredientName = {"rau", "muoi", "tieu", "hanh"};
    String[] priceIngredient = {"100gram", "100gram", "100gram", "100gram"};
    int[] imgIngredient = {R.drawable.rauxanh, R.drawable.muoi, R.drawable.tieu, R.drawable.hanh};
    ListView lvDirection;
    String[] stepDirection ={"Step 1", "Step 2", "Step 3"};
    String[] desDirection = {"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        lvIngredient = findViewById(R.id.lvIngredient);
        IngredientAdapter ingredientAdapter = new IngredientAdapter(this, ingredientName, imgIngredient, priceIngredient);
        lvIngredient.setAdapter(ingredientAdapter);

        lvDirection = findViewById(R.id.lvDirection);
        DirectionAdapter directionAdapter = new DirectionAdapter(this, stepDirection, desDirection);
        lvDirection.setAdapter(directionAdapter);
        
    }
}