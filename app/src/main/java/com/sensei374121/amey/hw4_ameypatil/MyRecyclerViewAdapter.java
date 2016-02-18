package com.sensei374121.amey.hw4_ameypatil;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

/**
 * Created by Amey on 17-02-2016.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    //Declaring Variables
    private Context mContext;
    private List<Map<String,?>> mDataset;

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

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Clicked on the view", Toast.LENGTH_SHORT).show();
                    //TODO
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout,parent,false);
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
}
