package com.sensei374121.amey.hw4_ameypatil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_RecyclerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recycler_view_main_container, Fragment_recyclerview.newInstance())
                .commit();
    }
}
