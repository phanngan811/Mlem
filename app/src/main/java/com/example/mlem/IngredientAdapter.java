package com.example.mlem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class IngredientAdapter extends ArrayAdapter<String> {
    Context context;
    int[] images;
    String[] ingredientName;
    String[] ingredientPrice;
    public IngredientAdapter(@NonNull Context context, String[] ingredientName, int[] images, String[] ingredientPrice) {
        super(context, R.layout.single_ingredient, R.id.txt1, ingredientName);
        this.context = context;
        this.images = images;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View singleItem = convertView;
        IngredientViewHolder holder = null;
        if(singleItem == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layoutInflater.inflate(R.layout.single_ingredient, parent, false);
            holder = new IngredientViewHolder(singleItem);
            singleItem.setTag(holder);
        }else{
            holder = (IngredientViewHolder) singleItem.getTag();
        }
        holder.itemImage.setImageResource(images[position]);
        holder.ingredientName.setText(ingredientName[position]);
        holder.ingredientPrice.setText(ingredientPrice[position]);
        singleItem.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "You clicked " + ingredientName[position], Toast.LENGTH_SHORT).show();
            }
        });
        return singleItem;
    }
}
