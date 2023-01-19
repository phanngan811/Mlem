package com.example.mlem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mlem.ViewModel.BlogVM;
import com.squareup.picasso.Picasso;

public class BlogDetail extends AppCompatActivity {
    private BlogVM blogVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);

        TextView blogName = findViewById(R.id.blogNameText);
        TextView authorName = findViewById(R.id.authorNameText);
        RatingBar rating = findViewById(R.id.ratingBar);
        TextView blogDescription = findViewById(R.id.dishDescriptionText);
        ImageView dishImg = findViewById(R.id.dishImage);

        ImageButton viewRecipeBtn = findViewById(R.id.viewRecipeBtn);

        Intent intent = getIntent();
        String blogId = intent.getStringExtra("blogId");

        blogVM = new ViewModelProvider(this).get(BlogVM.class);
        blogVM.setId(blogId);
        blogVM.getOne();
        blogVM.getBlog().observe(this, blog -> {
            blogName.setText(blog.getTitle());
            blogDescription.setText(blog.getContent());
            authorName.setText(blog.getAuthor());
            if(blog.getImageUrl() != null){
                Picasso.get().load(blog.getImageUrl()).into(dishImg);
            }
        });

        viewRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent i = new Intent(BlogDetail.this, RecipeDetail.class);
                i.putExtra("recipeId", blogVM.getBlog().getValue().getRecipeId());
                startActivity(i);
                finish();
            }
        });
    }
}