package com.example.firebase_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void add(View v){
        Intent i = new Intent(MainActivity.this, AddRecord.class);
        startActivity(i);
    }

    public void viewSingle(View v){
        Intent i = new Intent(MainActivity.this, ViewSingle.class);
        startActivity(i);
    }

    public void viewAll(View v){
        Intent i = new Intent(MainActivity.this, ViewAllRecords.class);
        startActivity(i);
    }

    public void delete(View v){
        Intent i = new Intent(MainActivity.this, DeleteRecord.class);
        startActivity(i);
    }

    public void update(View v){
        Intent i = new Intent(MainActivity.this, UpdateRecord.class);
        startActivity(i);
    }

}
