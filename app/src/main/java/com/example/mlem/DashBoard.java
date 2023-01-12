package com.example.mlem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mlem.Adapter.DashboardIngredientRVAdapter;
import com.example.mlem.Model.Ingredient;
import com.example.mlem.ViewModel.DashboardVM;

import java.util.ArrayList;
import java.util.List;

public class DashBoard extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<IngredientData> ingredientDataArrayList;
    DashboardVM mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        mViewModel = new ViewModelProvider(this).get(DashboardVM.class);
        recyclerView=findViewById(R.id.idIngredientRV);

       // added data from arraylist to adapter class.
        mViewModel.getIngredients();
        DashboardIngredientRVAdapter adapter=new DashboardIngredientRVAdapter(this);
        mViewModel.getResult().observe(this, new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(List<Ingredient> ingredients) {
                adapter.setIngredientDataArrayList((ArrayList<Ingredient>) ingredients);
            }
        });

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}