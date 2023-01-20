package com.example.mlem;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
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
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

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
            assert getSupportActionBar() != null;
            getSupportActionBar().setTitle(blog.getTitle());

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

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;

    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}