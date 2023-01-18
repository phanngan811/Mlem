package com.example.mlem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.Model.Recipe;
import com.example.mlem.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashboardRecipeRVAdapter extends RecyclerView.Adapter<DashboardRecipeRVAdapter.RecyclerViewHolder> {
    private ArrayList<Recipe> recipeDataArrayList;
    private Context mcontext;

    public DashboardRecipeRVAdapter(Context mcontext) {
        this.mcontext = mcontext;
        recipeDataArrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_item, parent, false);
        return new DashboardRecipeRVAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        Recipe recipeData = recipeDataArrayList.get(position);
        holder.name.setText(recipeData.getName());
        if(recipeData.getImageUrl() != null){
            ImageView imgView = (ImageView) holder.image;
            Picasso.get().load(recipeData.getImageUrl()).into(imgView);
        }
    }

    @Override
    public int getItemCount() {
        return recipeDataArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView image;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.dashboardItemTitle);
            image = itemView.findViewById(R.id.dashboardItemImage);
        }
    }

    public void setRecipeDataArrayList(ArrayList<Recipe> recipeDataArrayList) {
        this.recipeDataArrayList = recipeDataArrayList;
        notifyDataSetChanged();
    }
}
