package com.seu.kfmcapp;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    Button btnHit;
    TextView txtJson;
    private ArrayList<Major> majorsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHit = (Button) findViewById(R.id.btnHit);
        txtJson = (TextView) findViewById(R.id.tvJsonItem);

        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , TrainingRequest.class);
                startActivity(intent);
                getAllMajors();
            }
        });
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void getAllMajors() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://90f3-2001-16a2-c081-d45d-e07b-4b1-9ea2-fd28.eu.ngrok.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // on below line we are calling a method to get all the courses from API.
        Call<ArrayList<Major>> call = retrofitAPI.getAllMajors();

        // on below line we are calling method to enqueue and calling
        // all the data from array list.
        call.enqueue(new Callback<ArrayList<Major>>() {
            @Override
            public void onResponse(Call<ArrayList<Major>> call, Response<ArrayList<Major>> response) {
                // inside on response method we are checking
                // if the response is success or not.
                if (response.isSuccessful()) {

                    // below line is to add our data from api to our array list.
                    majorsArrayList = response.body();
                    // below line we are running a loop to add data to our adapter class.
                    for (int i = 0; i < majorsArrayList.size(); i++) {
                Major major = majorsArrayList.get(i);
                String x = major.getName();
                        Log.e(TAG, "onResponse: "+x);

                    }


                }
            }

            @Override
            public void onFailure(Call<ArrayList<Major>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail to get data", Toast.LENGTH_SHORT).show();

            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.navigation_menu, menu);
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_applyForTrainingRequest:
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_LONG).show();
                return true;
            case R.id.nav_myTrainingRequestStatus:
                Toast.makeText(MainActivity.this, "clicked3", Toast.LENGTH_LONG).show();
                return true;
        }

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);


        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_applyForTrainingRequest:
                    Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.nav_myTrainingRequestStatus:
                    Toast.makeText(MainActivity.this, "clicked3", Toast.LENGTH_LONG).show();
                    return true;
            }

        return false;
    }

}
