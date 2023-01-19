package com.example.mlem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mlem.Adapter.DashboardBlogRVAdapter;
import com.example.mlem.Adapter.DashboardIngredientRVAdapter;
import com.example.mlem.Adapter.DashboardRecipeRVAdapter;
import com.example.mlem.Model.Blog;
import com.example.mlem.Model.Ingredient;
import com.example.mlem.Model.Recipe;
import com.example.mlem.ViewModel.DashboardVM;

import java.util.ArrayList;
import java.util.List;

public class DashBoard extends AppCompatActivity implements InterfaceGroup {

    RecyclerView ingredientsRv;
    RecyclerView blogsRv;
    RecyclerView recipeRv;
    DashboardVM mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        mViewModel = new ViewModelProvider(this).get(DashboardVM.class);
        ingredientsRv = findViewById(R.id.idIngredientRV);
        blogsRv = findViewById(R.id.idBlogRV);
        recipeRv = findViewById(R.id.idRecipeRV);

       // added data from arraylist to adapter class.
        mViewModel.getIngredients();
        mViewModel.getBlogs();
        mViewModel.getRecipes();
        // setup RecyclerView adapters
        DashboardIngredientRVAdapter ingredientAdapter=new DashboardIngredientRVAdapter(this, this);
        DashboardBlogRVAdapter blogAdapter = new DashboardBlogRVAdapter(this, this);
        DashboardRecipeRVAdapter recipeAdapter = new DashboardRecipeRVAdapter(this, this);

        mViewModel.getIngredientResults().observe(this, new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(List<Ingredient> ingredients) {
                ingredientAdapter.setIngredientDataArrayList((ArrayList<Ingredient>) ingredients);
            }
        });
        mViewModel.getBlogResults().observe(this, new Observer<List<Blog>>() {
            @Override
            public void onChanged(List<Blog> blogs) {
                blogAdapter.setBlogDataArrayList((ArrayList<Blog>) blogs);
            }
        });
        mViewModel.getRecipeResults().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                recipeAdapter.setRecipeDataArrayList((ArrayList<Recipe>) recipes);
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

        LinearLayoutManager recipeListLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recipeRv.setLayoutManager(recipeListLayoutManager);
        recipeRv.setAdapter(recipeAdapter);
    }
    @Override
    public void onClickIngredient(int position) {
        Intent i = new Intent(DashBoard.this, IngredientDetail.class);
        i.putExtra("ingredientId", mViewModel.getIngredientResults().getValue().get(position).getId());
        startActivity(i);
    }

    @Override
    public void onClickBlog(int position) {
        Intent i = new Intent(DashBoard.this, BlogDetail.class);
        i.putExtra("blogId", mViewModel.getBlogResults().getValue().get(position).getId());
        startActivity(i);
    }

    @Override
    public void onClickRecipe(int position) {
        Intent i = new Intent(DashBoard.this, RecipeDetail.class);
        i.putExtra("recipeId", mViewModel.getRecipeResults().getValue().get(position).getId());
        startActivity(i);
    }
    public void onClickSetting(View v){
        Intent i = new Intent(DashBoard.this, Setting.class);
        startActivity(i);
    }
}