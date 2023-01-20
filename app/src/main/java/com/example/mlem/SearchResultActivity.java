package com.example.mlem;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.mlem.Enum.SearchByType;
import com.example.mlem.Enum.SearchType;
import com.example.mlem.ViewModel.SearchResultViewModel;
import com.example.mlem.databinding.ActivitySearchResultBinding;

public class SearchResultActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ActivitySearchResultBinding mBinding;
    private SearchResultViewModel mViewModel;

    private MutableLiveData<String> searchQuery;
    private MutableLiveData<SearchByType> searchByType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySearchResultBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Search");

        searchQuery = new MutableLiveData<>();
        searchByType = new MutableLiveData<>();

        replaceFragment(new IngredientSearchResultFragment());
        mViewModel = new ViewModelProvider(this).get(SearchResultViewModel.class);

        initListeners();
        initObservers();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.search_by_type_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBinding.spinnerSearchBy.setAdapter(adapter);
        mBinding.spinnerSearchBy.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.searchBar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                setSearchQuery(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                setSearchQuery(s);
                return false;
            }
        });

        return true;
    }

    public LiveData<String> getSearchQuery() {
        return searchQuery;
    }

    private void setSearchQuery(String searchQuery) {
        this.searchQuery.setValue(searchQuery);
    }

    public LiveData<SearchByType> getSearchByType() {
        return searchByType;
    }

    private void setSearchByType(SearchByType searchByType) {
        this.searchByType.setValue(searchByType);
    }

    private void initListeners() {
        mBinding.btnTypeBlog.setOnClickListener(view -> {
            mViewModel.changeSearchType(SearchType.BLOG);
        });
        mBinding.btnTypeIngredient.setOnClickListener(view -> {
            mViewModel.changeSearchType(SearchType.INGREDIENT);
        });
        mBinding.btnTypeRecipe.setOnClickListener(view -> {
            mViewModel.changeSearchType(SearchType.RECIPE);
        });
    }

    private void initObservers() {
        int primary = ContextCompat.getColor(this, R.color.primary);
        int neutral = ContextCompat.getColor(this, R.color.neutral_400);

        mViewModel.getSearchType().observe(this, searchType -> {
            if (searchType == SearchType.RECIPE) {
                replaceFragment(new RecipeSearchResultFragment());
                mBinding.btnTypeRecipe.setBackgroundColor(primary);
                mBinding.btnTypeBlog.setBackgroundColor(neutral);
                mBinding.btnTypeIngredient.setBackgroundColor(neutral);
            } else if (searchType == SearchType.BLOG) {
                replaceFragment(new BlogSearchResultFragment());
                mBinding.btnTypeRecipe.setBackgroundColor(neutral);
                mBinding.btnTypeBlog.setBackgroundColor(primary);
                mBinding.btnTypeIngredient.setBackgroundColor(neutral);
            } else {
                replaceFragment(new IngredientSearchResultFragment());
                mBinding.btnTypeRecipe.setBackgroundColor(neutral);
                mBinding.btnTypeBlog.setBackgroundColor(neutral);
                mBinding.btnTypeIngredient.setBackgroundColor(primary);
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int searchByType = adapterView.getSelectedItemPosition();
        if (searchByType == 0) {
            setSearchByType(SearchByType.NAME);
        } else {
            setSearchByType(SearchByType.TAG);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}