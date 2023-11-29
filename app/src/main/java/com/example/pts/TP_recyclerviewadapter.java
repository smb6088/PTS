package com.example.pts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TP_recyclerviewadapter extends RecyclerView.Adapter<TP_recyclerviewadapter.MyViewHolder> {
    HireaTutor2 context;
    ArrayList<tutorsearch_model> list;


    public TP_recyclerviewadapter(HireaTutor2 context, ArrayList<tutorsearch_model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_row,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        tutorsearch_model tutor = list.get(position);
        holder.txtname.setText(tutorsearch_model.getName());
        holder.txtloc.setText(tutorsearch_model.getLocation());
        holder.txtloc.setText(tutorsearch_model.getRating());
    }

    @Override
    public int getItemCount() {
        return list.size();
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
