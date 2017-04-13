package com.example.sajini.homeautomationmobileapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by sajini on 4/12/17.
 */

public class DeviceActivity extends AppCompatActivity {


    static ArrayList topics = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
    }

    public void setTopics(){

    }

    public  ArrayList getTopics(){
        return topics;
    }
}
