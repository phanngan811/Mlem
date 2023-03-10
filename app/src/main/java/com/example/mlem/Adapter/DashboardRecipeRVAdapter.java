package com.example.mlem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.InterfaceGroup;
import com.example.mlem.Model.Recipe;
import com.example.mlem.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashboardRecipeRVAdapter extends RecyclerView.Adapter<DashboardRecipeRVAdapter.RecyclerViewHolder> {
    private ArrayList<Recipe> recipeDataArrayList;
    private Context mcontext;

    private final InterfaceGroup interfaceGroup;

    public DashboardRecipeRVAdapter(Context mcontext, InterfaceGroup interfaceGroup) {
        this.mcontext = mcontext;
        recipeDataArrayList = new ArrayList<>();
        this.interfaceGroup = interfaceGroup;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_item, parent, false);
        return new DashboardRecipeRVAdapter.RecyclerViewHolder(view, interfaceGroup);
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

        public RecyclerViewHolder(@NonNull View itemView, InterfaceGroup interfaceGroup) {
            super(itemView);
            name = itemView.findViewById(R.id.dashboardItemTitle);
            image = itemView.findViewById(R.id.dashboardItemImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(interfaceGroup != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            interfaceGroup.onClickRecipe(pos);
                        }
                    }
                }
            });
        }
    }

    public void setRecipeDataArrayList(ArrayList<Recipe> recipeDataArrayList) {
        this.recipeDataArrayList = recipeDataArrayList;
        notifyDataSetChanged();
    }
}
