package com.example.mlem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.Model.Ingredient;
import com.example.mlem.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<Ingredient> ingredientDataArrayList;
    private Context mcontext;

    public RecyclerViewAdapter(Context mcontext) {
        ingredientDataArrayList = new ArrayList<>();
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        Ingredient ingredientData = ingredientDataArrayList.get(position);
        holder.name.setText(ingredientData.getName());
//        holder.image.setImageResource(ingredientData.getImgid());
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return ingredientDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView image;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ingredientName);
            image = itemView.findViewById(R.id.ingredientImage);
        }
    }

    public void setIngredientDataArrayList(ArrayList<Ingredient> ingredientArrayList) {
        this.ingredientDataArrayList = ingredientArrayList;
        notifyDataSetChanged();
    }
}