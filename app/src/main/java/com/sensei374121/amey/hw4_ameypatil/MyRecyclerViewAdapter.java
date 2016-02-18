package com.sensei374121.amey.hw4_ameypatil;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Amey on 17-02-2016.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    //Declaring Variables
    MovieData moviedata = new MovieData();
    private Context mContext;
    private List<Map<String,?>> mDataset;
    private OnItemClickListener mItemClickListener;

    public MyRecyclerViewAdapter(Context myContext, List<Map<String,?>> myDataset){
        mContext = myContext;
        mDataset = myDataset;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView vTitle,vDescription;
        public ImageView vImage;
        public CheckBox vCheckbox;
        public ViewHolder(View v){
            super(v);
            vTitle = (TextView) v.findViewById(R.id.item_title);
            vImage = (ImageView) v.findViewById(R.id.item_image);
            vDescription = (TextView) v.findViewById(R.id.item_description);
            vCheckbox = (CheckBox) v.findViewById(R.id.item_checkbox);

            vCheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mItemClickListener != null){
                        mItemClickListener.onCheckboxClicked(v,getPosition());
                    }
                }
            });

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener !=null){
                        mItemClickListener.onItemClick(v, getPosition());
                    }
                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(mItemClickListener != null){
                        mItemClickListener.onItemLongClick(v,getPosition());
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType){
            case 1: v = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_layout, parent, false);
                    break;
            case 0: v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_layout_other,parent,false);
                    break;
            default: v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_layout_other, parent, false);
        }

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map<String,?> movie = mDataset.get(position);
        holder.vTitle.setText((CharSequence) movie.get("name"));
        holder.vDescription.setText((CharSequence) movie.get("description"));
        holder.vImage.setImageResource((Integer) movie.get("image"));
        holder.vCheckbox.setChecked((Boolean) movie.get("selection"));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface OnItemClickListener{
        public void onItemClick(View view,int position);
        public void onItemLongClick(View view, int position);
        public void onCheckboxClicked(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        double rating = (double) moviedata.getItem(position).get("rating");
        if(rating > 8.0){
            return 1;
        }
        else{
            return 0;
        }
    }
}
