package com.example.mlem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class DashBoard extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<IngredientData> ingredientDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        recyclerView=findViewById(R.id.idCourseRV);

        // created new array list..
        ingredientDataArrayList =new ArrayList<>();

        // added data to array list
        ingredientDataArrayList.add(new IngredientData("DSA",R.drawable.mlemmm));
        ingredientDataArrayList.add(new IngredientData("JAVA",R.drawable.mlemmm));
        ingredientDataArrayList.add(new IngredientData("C++",R.drawable.mlemmm));
        ingredientDataArrayList.add(new IngredientData("Python",R.drawable.mlemmm));
        ingredientDataArrayList.add(new IngredientData("Node Js",R.drawable.mlemmm));

        // added data from arraylist to adapter class.
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(ingredientDataArrayList,this);

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}