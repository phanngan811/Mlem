package com.example.mlem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.mlem.Adapter.BlogRVAdapter;
import com.example.mlem.Adapter.DashboardBlogRVAdapter;
import com.example.mlem.Adapter.DashboardIngredientRVAdapter;
import com.example.mlem.Model.Blog;
import com.example.mlem.Model.Ingredient;
import com.example.mlem.ViewModel.DashboardVM;

import java.util.ArrayList;
import java.util.List;

public class DashBoard extends AppCompatActivity implements IngredientInterface {

    RecyclerView ingredientsRv;
    RecyclerView blogsRv;
    ArrayList<IngredientData> ingredientDataArrayList;
    DashboardVM mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        mViewModel = new ViewModelProvider(this).get(DashboardVM.class);
        ingredientsRv = findViewById(R.id.idIngredientRV);
        blogsRv = findViewById(R.id.idBlogRV);

       // added data from arraylist to adapter class.
        mViewModel.getIngredients();
        mViewModel.getBlogs();
        DashboardIngredientRVAdapter ingredientAdapter=new DashboardIngredientRVAdapter(this, this);
        DashboardBlogRVAdapter blogAdapter = new DashboardBlogRVAdapter(this);
//        BlogRVAdapter blogAdapter = new BlogRVAdapter(this);
        mViewModel.getIngredientResults().observe(this, new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(List<Ingredient> ingredients) {
                ingredientAdapter.setIngredientDataArrayList((ArrayList<Ingredient>) ingredients);
            }
        });
        mViewModel.getBlogResults().observe(this, new Observer<List<Blog>>() {
            @Override
            public void onChanged(List<Blog> blogs) {
//                ingredientAdapter.setBlogDataArrayList((ArrayList<Blog>) blogs);
                blogAdapter.setBlogDataArrayList((ArrayList<Blog>) blogs);
            }
        });

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager ingredientListLayoutManager=new GridLayoutManager(this,2);

        // at last set adapter to recycler view.
        ingredientsRv.setLayoutManager(ingredientListLayoutManager);
        ingredientsRv.setAdapter(ingredientAdapter);

        LinearLayoutManager blogListLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        blogsRv.setLayoutManager(blogListLayoutManager);
        blogsRv.setAdapter(blogAdapter);
    }
    @Override
    public void onClick(int position) {
        Intent i = new Intent(DashBoard.this, IngredientDetail.class);
        i.putExtra("ingredientId", mViewModel.getIngredientResults().getValue().get(position).getId());
        startActivity(i);
    }
}