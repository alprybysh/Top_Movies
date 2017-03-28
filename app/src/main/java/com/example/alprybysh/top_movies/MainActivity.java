package com.example.alprybysh.top_movies;

import android.content.Context;
import android.nfc.Tag;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesOnClickListenerHandler {

    MoviesAdapter adapter;
    Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("message", "5678");

        setContentView(R.layout.activity_main);
        Log.v("message", "12345 ");

        String[] data = {"1", "2", "3", "4", "5","6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
                "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
                "41", "42",  "43", "44", "45", "46", "47", "48",
        "41", "42",  "43", "44", "45", "46", "47", "48", "41", "42",  "43", "44", "45", "46", "47", "48"};

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.display_list_of_movies);
        int numberOfColumns = 4;

        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new MoviesAdapter(this, data, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(String s) {
        if (mToast != null) {
            mToast.cancel();
        }

        String toastMessage = "Item # "+ s+ "clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();

    }
}
