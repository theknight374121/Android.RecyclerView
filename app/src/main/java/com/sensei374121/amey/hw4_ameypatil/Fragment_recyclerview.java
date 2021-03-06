package com.sensei374121.amey.hw4_ameypatil;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import jp.wasabeef.recyclerview.animators.LandingAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by Amey on 17-02-2016.
 */
public class Fragment_recyclerview extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyRecyclerViewAdapter mRecyclerAdapter;
    private MovieData moviedata = new MovieData();
    private HashMap<String,?> movie_obj;
    private Parcelable mListState;

    public static Fragment_recyclerview newInstance(){
        Fragment_recyclerview frag_obj = new Fragment_recyclerview();
        Bundle args = new Bundle();

        //to save any arguments if needed

        frag_obj.setArguments(args);
        return frag_obj;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview;
        rootview=inflater.inflate(R.layout.fragment_recyclerview,container,false);

        //setting up the interface for onclick from activity
        final onItemClickListener int_obj;
        int_obj = (onItemClickListener) getContext();

        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.main_recyclerview);

        mRecyclerView.setHasFixedSize(true);

        //set Layout
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Setting up animations for items
        mRecyclerView.setItemAnimator(new LandingAnimator());

        //set Adapter
        mRecyclerAdapter = new MyRecyclerViewAdapter(getActivity(), moviedata.getMoviesList());
        //set animations for the adapter
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(mRecyclerAdapter);
        scaleAdapter.setDuration(500);

        mRecyclerView.setAdapter(scaleAdapter);

        //implement the interface
        mRecyclerAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int_obj.itemClickListener(moviedata.getItem(position));

            }

            @Override
            public void onItemLongClick(View view, int position) {
                moviedata.moviesList.add(position, (Map<String, ?>) moviedata.getItem(position).clone());
                mRecyclerAdapter.notifyItemInserted(position);
            }

            @Override
            public void onCheckboxClicked(View view, int position) {
                boolean value = (boolean) moviedata.getItem(position).get("selection");
                if(value){
                    moviedata.getItem(position).put("selection",false);

                }else{
                    moviedata.getItem(position).put("selection",true);
                }
//                TextView descriptionhidden = (TextView) view.findViewById(R.id.item_description);
//                descriptionhidden.setVisibility(View.VISIBLE);
            }
        });

        //setting up the button listeners
        Button clearall = (Button) rootview.findViewById(R.id.button_clearall);
        Button selectall = (Button) rootview.findViewById(R.id.selectall_button);
        Button delete = (Button) rootview.findViewById(R.id.delete_button);

        if (clearall != null && selectall != null && delete != null) {
            clearall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mRecyclerAdapter.getItemCount(); i++) {
                        HashMap item = moviedata.getItem(i);
                        item.put("selection",false);
                    }
                    mRecyclerAdapter.notifyItemRangeChanged(0, mRecyclerAdapter.getItemCount());
                }
            });
            selectall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mRecyclerAdapter.getItemCount(); i++) {
                        HashMap item = moviedata.getItem(i);
                        item.put("selection",true);
                    }
                    mRecyclerAdapter.notifyItemRangeChanged(0, mRecyclerAdapter.getItemCount());
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mRecyclerAdapter.getItemCount(); i++) {
                        HashMap<String,Boolean> item = (HashMap<String,Boolean>)moviedata.getItem(i);
                        boolean result = (boolean) item.get("selection");
                        if (result==true){
                            moviedata.moviesList.remove(i);
                            mRecyclerAdapter.notifyItemRemoved(i);
                        }
                    }
                    //mRecyclerAdapter.notifyItemRangeChanged(0,mRecyclerAdapter.getItemCount());
                }
            });
        }


        return rootview;

    }


    public interface onItemClickListener{
        public void itemClickListener(HashMap<String,?> movie_obj );
    }
}
