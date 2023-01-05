package com.example.mlem;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class IngredientViewHolder {
    ImageView itemImage;
    TextView ingredientName;
    TextView ingredientPrice;

    IngredientViewHolder(View v){
        itemImage = v.findViewById(R.id.imageView8);
        ingredientName = v.findViewById(R.id.txt1);
        ingredientPrice = v.findViewById(R.id.txt2);
    }
}
