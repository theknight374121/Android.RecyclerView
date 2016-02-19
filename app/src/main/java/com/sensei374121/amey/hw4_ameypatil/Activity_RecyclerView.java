package com.sensei374121.amey.hw4_ameypatil;

import android.graphics.Movie;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

public class Activity_RecyclerView extends AppCompatActivity implements Fragment_recyclerview.onItemClickListener {

    private MovieData movieData = new MovieData();
    private HashMap<String,?> movie_obj;
    Fragment frag_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        if(savedInstanceState==null){
            frag_state=Fragment_recyclerview.newInstance();

        }else {
            frag_state = getSupportFragmentManager().getFragment(savedInstanceState, "FRAG_STATE");
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recycler_view_main_container, frag_state)
                .commit();

    }

    @Override
    public void itemClickListener(HashMap<String, ?> movie_obj) {
        frag_state=Fragment_moviedata.newInstance(movie_obj);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recycler_view_main_container,frag_state)
                .addToBackStack(null)
                .commit();
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState,"FRAG_STATE",frag_state);
    }
}
