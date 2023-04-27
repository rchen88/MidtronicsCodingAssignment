package com.example.midtronicsassignment;

import android.widget.TextView;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class CountryDetailActivity extends AppCompatActivity {
    private TextView mNameTextView;
    private TextView mCapitalTextView;
    private TextView mPopulationTextView;
    private TextView mAreaTextView;
    private TextView mRegionTextView;
    private TextView mSubRegionTextView;
    private String mCountryName;
    private String mCapital;
    private String mPopulation;
    private String mArea;
    private String mRegion;
    private String mSubRegion;
    private static final String API_URL = "https://restcountries.com/v3.1/name/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countrydetail);
        mNameTextView = findViewById(R.id.country_name_textview);
        mCapitalTextView = findViewById(R.id.capital_textview);
        mPopulationTextView = findViewById(R.id.population_textview);
        mAreaTextView = findViewById(R.id.area_textview);
        mRegionTextView = findViewById(R.id.region_textview);
        mSubRegionTextView = findViewById(R.id.subregion_textview);
        Bundle bundle = getIntent().getExtras();
        mCountryName = bundle.getString("countryName");
        getSupportActionBar().setTitle(mCountryName);
        new GetCountryDetailsTask().execute(API_URL + mCountryName);
    }


    private class GetCountryDetailsTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    response += line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                JSONObject countryNames = jsonObject.getJSONObject("name");
                mNameTextView.setText(countryNames.getString("common"));

                if (jsonObject.has("capital")) {
                    mCapital = jsonObject.getString("capital").replace("[\"","").replace("\"]","");
                    mCapitalTextView.setText("Capital: " + mCapital);
                }

                if (jsonObject.has("population")) {
                    mPopulation = jsonObject.getString("population");
                    mPopulationTextView.setText("Population: " + mPopulation + " people");
                }

                if (jsonObject.has("area")) {
                    mArea = jsonObject.getString("area");
                    mAreaTextView.setText("Area: " + mArea + " km squared");
                }

                if (jsonObject.has("region")) {
                    mRegion = jsonObject.getString("region");
                    mRegionTextView.setText("Region: " + mRegion);
                }

                if (jsonObject.has("subregion")) {
                    mSubRegion = jsonObject.getString("subregion");
                    mSubRegionTextView.setText("Subregion: " + mSubRegion);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}


