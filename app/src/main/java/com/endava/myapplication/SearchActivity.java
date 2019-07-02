package com.endava.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.endava.myapplication.adaptor.SearchAdaptor;
import com.endava.myapplication.repository.DBHandler;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdaptor adaptor;

    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();

    DBHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_employees);

        recyclerView = findViewById(R.id.recycler_search);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        materialSearchBar = findViewById(R.id.search_bar);

        database = new DBHandler(this);

        materialSearchBar.setHint("Search");
        materialSearchBar.setCardViewElevation(10);
        loadSuggestList();
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<String> suggest = new ArrayList<>();
                for (String search : suggestList) {
                    if (search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled)
                    recyclerView.setAdapter(adaptor);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        adaptor = new SearchAdaptor(this, database.getAllEmployees());
        recyclerView.setAdapter(adaptor);
    }

    private void startSearch(String text) {
        adaptor = new SearchAdaptor(this, database.getEmployeeByName(text));
        recyclerView.setAdapter(adaptor);
    }

    private void loadSuggestList() {
        suggestList = database.getAllEmployeesName();
        materialSearchBar.setLastSuggestions(suggestList);
    }

}
