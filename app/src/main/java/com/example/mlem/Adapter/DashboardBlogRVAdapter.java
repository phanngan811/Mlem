package com.example.mlem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.IngredientDetail;
import com.example.mlem.IngredientInterface;
import com.example.mlem.Model.Blog;
import com.example.mlem.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashboardBlogRVAdapter extends RecyclerView.Adapter<DashboardBlogRVAdapter.RecyclerViewHolder> {
    private ArrayList<Blog> blogDataArrayList;
    private Context mcontext;
    private final IngredientInterface ingredientInterface;

    public DashboardBlogRVAdapter(Context mcontext, IngredientInterface ingredientInterface) {
        this.mcontext = mcontext;
        blogDataArrayList = new ArrayList<>();
        this.ingredientInterface = ingredientInterface;
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_item, parent, false);
        return new RecyclerViewHolder(view, ingredientInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull DashboardBlogRVAdapter.RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        Blog blogData = blogDataArrayList.get(position);
        holder.name.setText(blogData.getTitle());
        if(blogData.getImageUrl() != null){
            ImageView imgView = (ImageView) holder.image;
            Picasso.get().load(blogData.getImageUrl()).into(imgView);
        }
    }

    @Override
    public int getItemCount() {
        return blogDataArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView image;

        public RecyclerViewHolder(@NonNull View itemView, IngredientInterface ingredientInterface) {
            super(itemView);
            name = itemView.findViewById(R.id.dashboardBlogTitle);
            image = itemView.findViewById(R.id.dashboardBlogImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(ingredientInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            ingredientInterface.onClickBlog(pos);
                        }
                    }
                }
            });
        }
    }

    public void setBlogDataArrayList(ArrayList<Blog> blogDataArrayList) {
        this.blogDataArrayList = blogDataArrayList;
        notifyDataSetChanged();
    }
}
