package com.example.pts;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TP_recyclerviewadapter extends RecyclerView.Adapter<TP_recyclerviewadapter.MyViewHolder> {

    @NonNull
    @Override
    public TP_recyclerviewadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TP_recyclerviewadapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imgview;
        TextView txtname, txtrating,txtloc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgview = itemView.findViewById(R.id.imageSearchProfPic);
            txtname = itemView.findViewById(R.id.textSearchName);
            txtloc = itemView.findViewById(R.id.textSearchLocation);
            txtrating =itemView.findViewById(R.id.textSearchRating);
        }
    }
}
