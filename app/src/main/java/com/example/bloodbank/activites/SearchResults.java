package com.example.bloodbank.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.widget.TextView;

import com.example.bloodbank.Adapters.RequestAdapter;
import com.example.bloodbank.Adapters.SearchAdapter;
import com.example.bloodbank.R;
import com.example.bloodbank.datamodeling.Donors;
import com.example.bloodbank.datamodeling.RequestDataModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity {
    List<Donors> donorsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        donorsList = new ArrayList<>();
        String json;
        String city, bloodgroup;
        Intent intent = getIntent();
        json = intent.getStringExtra("json");
        city = intent.getStringExtra("city");
        bloodgroup = intent.getStringExtra("bloodgroup");
        TextView heading = findViewById(R.id.heading);
        String str = "Donors in " + city + " with blood group " + bloodgroup;
        heading.setText(str);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Donors>>() {
        }.getType();
        List<Donors> dataModels = gson.fromJson(json, type);
        if (dataModels != null && dataModels.isEmpty()) {
            heading.setText("No results found");
        } else if (dataModels != null) {
            donorsList.addAll(dataModels);
        }
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        SearchAdapter Adapter = new SearchAdapter(donorsList,SearchResults.this);
        recyclerView.setAdapter(Adapter);
    }
}
