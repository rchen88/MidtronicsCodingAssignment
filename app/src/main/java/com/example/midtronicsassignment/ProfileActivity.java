package com.example.midtronicsassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Views to display contact/work experience
        ScrollView scrollContactContainer = findViewById(R.id.scroll_contact_container);
        ScrollView scrollWorkContainer = findViewById(R.id.scroll_work_container);

        TextView contactText = findViewById(R.id.contact_text);
        TextView workExpText = findViewById(R.id.work_exp_text);

        // Change view when pressing on contact or work experience
        contactText.setOnClickListener(view -> {
            scrollContactContainer.setVisibility(View.VISIBLE);
            scrollWorkContainer.setVisibility(View.GONE);
        });

        workExpText.setOnClickListener(view -> {
            scrollWorkContainer.setVisibility(View.VISIBLE);
            scrollContactContainer.setVisibility(View.GONE);
        });

        // Change to country activity when clicked
        TextView myTextView = findViewById(R.id.countries_text);
        myTextView.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, CountriesActivity.class);
            startActivity(intent);
        });

    }
}