package com.example.mlem.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.IngredientInterface;
import com.example.mlem.Model.Ingredient;
import com.example.mlem.R;
import com.example.mlem.helper.Helper;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DashboardIngredientRVAdapter extends RecyclerView.Adapter<DashboardIngredientRVAdapter.RecyclerViewHolder> {

    private ArrayList<Ingredient> ingredientDataArrayList;
    private Context mcontext;
    private final IngredientInterface ingredientInterface;

    public DashboardIngredientRVAdapter(Context mcontext, IngredientInterface ingredientInterface) {
        ingredientDataArrayList = new ArrayList<>();
        this.mcontext = mcontext;
        this.ingredientInterface = ingredientInterface;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new RecyclerViewHolder(view, ingredientInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        Ingredient ingredientData = ingredientDataArrayList.get(position);
        holder.name.setText(ingredientData.getName());
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        holder.price.setText(Helper.CurrencyFormatter(ingredientData.getPrice()));
        if(ingredientData.getImageUrl() != null){
            ImageView imgView = (ImageView) holder.image;
            Picasso.get().load(ingredientData.getImageUrl()).into(imgView);
        }
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
        private TextView price;

        public RecyclerViewHolder(@NonNull View itemView, IngredientInterface ingredientInterface) {
            super(itemView);
            name = itemView.findViewById(R.id.ingredientName);
            image = itemView.findViewById(R.id.ingredientImage);
            price = itemView.findViewById(R.id.ingredientPrice);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(ingredientInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            ingredientInterface.onClick(pos);
                        }
                    }
                }
            });
        }
    }

    public void setIngredientDataArrayList(ArrayList<Ingredient> ingredientArrayList) {
        this.ingredientDataArrayList = ingredientArrayList;
        notifyDataSetChanged();
    }
}