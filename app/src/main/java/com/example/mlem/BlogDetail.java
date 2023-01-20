package com.example.mlem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.Adapter.BlogParagraphRVAdapter;
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
        RecyclerView blogParagraphs = findViewById(R.id.rvParagraphs);
        ImageView dishImg = findViewById(R.id.dishImage);

        LinearLayout viewRecipeBtn = findViewById(R.id.btnToRecipe);

        Intent intent = getIntent();
        String blogId = intent.getStringExtra("blogId");

        blogVM = new ViewModelProvider(this).get(BlogVM.class);
        blogVM.setId(blogId);
        blogVM.getOne();
        blogVM.getBlog().observe(this, blog -> {
            blogName.setText(blog.getTitle());
            authorName.setText(blog.getAuthor());
            if (blog.getImageUrl() != null) {
                Picasso.get().load(blog.getImageUrl()).into(dishImg);
            }
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            blogParagraphs.setLayoutManager(linearLayoutManager);
            BlogParagraphRVAdapter adapter = new BlogParagraphRVAdapter();
            blogParagraphs.setAdapter(adapter);
            if (blog.getContent() != null) {
                adapter.setParagraphs(blog.getContent());
            }
        });

        viewRecipeBtn.setOnClickListener(v -> {
            Intent i = new Intent(BlogDetail.this, RecipeDetail.class);
            i.putExtra("recipeId", Objects.requireNonNull(blogVM.getBlog().getValue()).getRecipeId());
            startActivity(i);
        });
    }
}