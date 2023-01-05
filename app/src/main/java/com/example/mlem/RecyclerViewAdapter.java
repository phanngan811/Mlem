package com.example.mlem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<IngredientData> ingredientDataArrayList;
    private Context mcontext;

    public RecyclerViewAdapter(ArrayList<IngredientData> ingredientDataArrayList, Context mcontext) {
        this.ingredientDataArrayList = ingredientDataArrayList;
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
        IngredientData ingredientData = ingredientDataArrayList.get(position);
        holder.name.setText(ingredientData.getTitle());
        holder.image.setImageResource(ingredientData.getImgid());
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
}