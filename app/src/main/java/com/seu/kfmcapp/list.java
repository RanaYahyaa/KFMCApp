package com.seu.kfmcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.seu.kfmcapp.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class list extends AppCompatActivity {

ArrayList<String> employees;
ArrayAdapter<String> listAdapter;
Handler mainHandler= new Handler();
ActivityMainBinding binding;
ListView lv ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);//binding.getRoot());
        lv = findViewById(R.id.employees);
        new fetchdata().start();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        initEmployeeList();
    }
    private void initEmployeeList(){
        employees= new ArrayList<>();
        listAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employees);
        lv.setAdapter(listAdapter);

        //binding.employees.setAdapter(listAdapter);
    }
    String data ="";
    class fetchdata extends Thread{
        public void run(){
            try{
                URL url= new URL("https://gist.githubusercontent.com/rominirani/8235702/raw/a50f7c449c41b6dc8eb87d8d393eeff62121b392/employees.json");
                HttpURLConnection httpURLConncetion= (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConncetion.getInputStream();
                BufferedReader bufferReader= new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line =bufferReader.readLine())!= null){
                    data= data+line;
                }
                if (!data.isEmpty()){
                    JSONObject jsonObject= new JSONObject(data);
                    JSONArray profiles= jsonObject.getJSONArray("Employees");
                    employees.clear();
                    for(int i=0;i<profiles.length();i++){
                        JSONObject names= profiles.getJSONObject(i);
                        String name =names.getString("firstName");
                        employees.add(name);
                    }

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mainHandler.post(new Runnable(){
                @Override
                public void run() {
                    listAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}