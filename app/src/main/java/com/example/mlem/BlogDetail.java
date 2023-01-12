package com.example.mlem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

public class BlogDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);

        TextView blogName = findViewById(R.id.blogNameText);
        TextView authorName = findViewById(R.id.authorNameText);
        RatingBar rating = findViewById(R.id.ratingBar);
        TextView blogDescription = findViewById(R.id.dishDescriptionText);

        ImageButton nextBtn = findViewById(R.id.nextBtn);
    }
}