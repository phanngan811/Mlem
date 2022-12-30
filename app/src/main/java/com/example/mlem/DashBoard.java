package com.example.mlem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class DashBoard extends AppCompatActivity {

    RecyclerView recyclerView;

    // Using ArrayList to store images data
    ArrayList courseImg = new ArrayList<>(Arrays.asList(R.drawable.mlemmm, R.drawable.mlemmm,
            R.drawable.mlemmm, R.drawable.mlemmm,
            R.drawable.mlemmm, R.drawable.mlemmm,
            R.drawable.mlemmm, R.drawable.mlemmm));
    ArrayList courseName = new ArrayList<>(Arrays.asList("Data Structure", "C++", "C#", "JavaScript", "Java",
            "C-Language", "HTML 5", "CSS"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        ArrayList ingredients = new ArrayList<>(Arrays.asList("Data Structure", "C++", "C#", "JavaScript", "Java",
                "C-Language", "HTML 5", "CSS"));

        // Getting reference of recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.shopIngredients);

        // Setting the layout as linear
        // layout for vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        // Sending reference and data to Adapter
        Adapter adapter = new Adapter(DashBoard.this, courseImg, courseName);

        // Setting Adapter to RecyclerView
        recyclerView.setAdapter(adapter);
    }
}