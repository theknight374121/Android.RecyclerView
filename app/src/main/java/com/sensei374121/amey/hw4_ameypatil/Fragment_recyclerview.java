package com.sensei374121.amey.hw4_ameypatil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Amey on 17-02-2016.
 */
public class Fragment_recyclerview extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyRecyclerViewAdapter mRecyclerAdapter;
    private MovieData moviedata = new MovieData();

    public static Fragment_recyclerview newInstance(){
        Fragment_recyclerview frag_obj = new Fragment_recyclerview();
        Bundle args = new Bundle();

        //to save any arguments if needed

        frag_obj.setArguments(args);
        return frag_obj;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview;
        rootview=inflater.inflate(R.layout.fragment_recyclerview,container,false);

        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.main_recyclerview);

        mRecyclerView.setHasFixedSize(true);

        //set Layout
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //set Adapter
        mRecyclerAdapter = new MyRecyclerViewAdapter(getActivity(), moviedata.getMoviesList());
        mRecyclerView.setAdapter(mRecyclerAdapter);

        //implement the interface
        mRecyclerAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                //TODO implement onItemClick
                Toast.makeText(getContext(),"On item clicked",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //TODO implemetn on ITEM long click
                moviedata.moviesList.remove(position);
                mRecyclerAdapter.notifyItemRemoved(position);
            }
        });



        return rootview;
    }
}
