package com.example.mlem;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mlem.Adapter.DashboardBlogRVAdapter;
import com.example.mlem.Adapter.DashboardIngredientRVAdapter;
import com.example.mlem.Adapter.DashboardRecipeRVAdapter;
import com.example.mlem.Model.Blog;
import com.example.mlem.Model.Ingredient;
import com.example.mlem.Model.Recipe;
import com.example.mlem.ViewModel.DashboardVM;
import com.example.mlem.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment implements InterfaceGroup {

    private RecyclerView ingredientsRv;
    private RecyclerView blogsRv;
    private RecyclerView recipeRv;
    private DashboardVM mViewModel;
    DashboardIngredientRVAdapter ingredientAdapter;
    DashboardBlogRVAdapter blogAdapter;
    DashboardRecipeRVAdapter recipeAdapter;
    private View mView;
    private FragmentDashboardBinding mBinding;


    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DashboardVM.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentDashboardBinding.inflate(inflater, container, false);
        mView = mBinding.getRoot();


        ingredientsRv = mView.findViewById(R.id.idIngredientRV);
        blogsRv = mView.findViewById(R.id.idBlogRV);
        recipeRv = mView.findViewById(R.id.idRecipeRV);

        initAdapters();
        initObservers();
        return mView;
    }


    private void initObservers() {
        mViewModel.getIngredients();
        mViewModel.getBlogs();
        mViewModel.getRecipes();


        mViewModel.getIngredientResults().observe(getActivity(), new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(List<Ingredient> ingredients) {
                ingredientAdapter.setIngredientDataArrayList((ArrayList<Ingredient>) ingredients);
            }
        });
        mViewModel.getBlogResults().observe(getActivity(), new Observer<List<Blog>>() {
            @Override
            public void onChanged(List<Blog> blogs) {
                blogAdapter.setBlogDataArrayList((ArrayList<Blog>) blogs);
            }
        });
        mViewModel.getRecipeResults().observe(getActivity(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                recipeAdapter.setRecipeDataArrayList((ArrayList<Recipe>) recipes);
            }
        });
    }

    private void initAdapters() {

        ingredientAdapter = new DashboardIngredientRVAdapter(getActivity(), this);
        blogAdapter = new DashboardBlogRVAdapter(getActivity(), this);
        recipeAdapter = new DashboardRecipeRVAdapter(getActivity(), this);

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager ingredientListLayoutManager=new GridLayoutManager(getActivity(),2);

        // at last set adapter to recycler view.
        ingredientsRv.setLayoutManager(ingredientListLayoutManager);
        ingredientsRv.setAdapter(ingredientAdapter);

        LinearLayoutManager blogListLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        blogsRv.setLayoutManager(blogListLayoutManager);
        blogsRv.setAdapter(blogAdapter);

        LinearLayoutManager recipeListLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recipeRv.setLayoutManager(recipeListLayoutManager);
        recipeRv.setAdapter(recipeAdapter);
    }

    @Override
    public void onClickBlog(int position) {
        Intent i = new Intent(mView.getContext(), BlogDetail.class);
        i.putExtra("blogId", mViewModel.getBlogResults().getValue().get(position).getId());
        startActivity(i);
    }

    @Override
    public void onClickIngredient(int position) {
        Intent i = new Intent(mView.getContext(), IngredientDetail.class);
        i.putExtra("ingredientId", mViewModel.getIngredientResults().getValue().get(position).getId());
        startActivity(i);
    }

    @Override
    public void onClickRecipe(int position) {
        Intent i = new Intent(mView.getContext(), RecipeDetail.class);
        i.putExtra("recipeId", mViewModel.getRecipeResults().getValue().get(position).getId());
        startActivity(i);
    }
}