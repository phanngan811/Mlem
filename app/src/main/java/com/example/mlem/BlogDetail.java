package com.example.mlem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mlem.ViewModel.BlogVM;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class BlogDetail extends AppCompatActivity {
    private BlogVM blogVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);

        TextView blogName = findViewById(R.id.blogNameText);
        TextView authorName = findViewById(R.id.authorNameText);
        TextView blogDescription = findViewById(R.id.dishDescriptionText);
        ImageView dishImg = findViewById(R.id.dishImage);

        LinearLayout viewRecipeBtn = findViewById(R.id.btnToRecipe);

        Intent intent = getIntent();
        String blogId = intent.getStringExtra("blogId");

        blogVM = new ViewModelProvider(this).get(BlogVM.class);
        blogVM.setId(blogId);
        blogVM.getOne();
        blogVM.getBlog().observe(this, blog -> {
            blogName.setText(blog.getTitle());
            blogDescription.setText(blog.getContent());
            authorName.setText(blog.getAuthor());
            if (blog.getImageUrl() != null) {
                Picasso.get().load(blog.getImageUrl()).into(dishImg);
            }
        });

        viewRecipeBtn.setOnClickListener(v -> {
            Intent i = new Intent(this, RecipeDetail.class);
            i.putExtra("recipeId", Objects.requireNonNull(blogVM.getBlog().getValue()).getId());
            startActivity(i);
        });
    }
}