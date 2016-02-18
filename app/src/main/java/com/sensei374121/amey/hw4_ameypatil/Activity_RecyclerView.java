package com.sensei374121.amey.hw4_ameypatil;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

public class Activity_RecyclerView extends AppCompatActivity implements Fragment_recyclerview.onItemClickListener {

    private MovieData movieData = new MovieData();
    private HashMap<String,?> movie_obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recycler_view_main_container, Fragment_recyclerview.newInstance())
                .commit();
    }

    @Override
    public void itemClickListener(HashMap<String, ?> movie_obj) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recycler_view_main_container,Fragment_moviedata.newInstance(movie_obj))
                .commit();
    }


}
