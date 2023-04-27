package com.example.midtronicsassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CountriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        ListView listView = findViewById(R.id.countries_list);
        String[] countries = getResources().getStringArray(R.array.countries_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, countries);
        listView.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            String countryName = adapter.getItem(i);
            Intent intent = new Intent(CountriesActivity.this, CountryDetailActivity.class);
            intent.putExtra("countryName", countryName);
            startActivity(intent);
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}