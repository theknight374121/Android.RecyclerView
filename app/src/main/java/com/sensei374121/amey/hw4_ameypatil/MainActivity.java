package com.sensei374121.amey.hw4_ameypatil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Fragment_home.buttonListener_Interface {

    int frag_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            //frag_state=MyFragment.newInstance(savedInstanceState.getInt("FRAGMENT_NO"));
            //Toast.makeText(MainActivity.this,"Inside saved",Toast.LENGTH_SHORT).show();
            //frag_state=getSupportFragmentManager().getFragment(savedInstanceState,"FRAGMENT_STATE");
            frag_id=savedInstanceState.getInt("FRAGMENT_ID");
        }else{
            //Toast.makeText(MainActivity.this,"Inside else",Toast.LENGTH_SHORT).show();
            //frag_state=MyFragment.newInstance(R.id.moviedata_layout);
            // frag_state=MovieData_Fragment.newInstance((HashMap<String,?>)moviedata_obj.getItem(2));
            frag_id=R.id.home_fragment;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, Fragment_home.newInstance(frag_id))
                .commit();
    }
    @Override
    public void aboutMeButtonListen() {
        frag_id=R.id.aboutme_fragment;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, Fragment_home.newInstance(frag_id))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void task2ButtonListen() {
        startActivity(new Intent(this, Activity_RecyclerView.class));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //SAve the fragment's state
        //getSupportFragmentManager().putFragment(outState, "FRAGMENT_STATE", frag_state);
        outState.putInt("FRAGMENT_ID", frag_id);
    }

}
