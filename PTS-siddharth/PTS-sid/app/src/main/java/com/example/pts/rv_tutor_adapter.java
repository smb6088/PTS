package com.example.pts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class rv_tutor_adapter extends RecyclerView.Adapter<rv_tutor_adapter.MyViewHolder>
{
    private final rv_interface Rv_interface;
    Context context;
    ArrayList<TutorSearchModel> tutorSearchModels;
    public rv_tutor_adapter(Context context, ArrayList<TutorSearchModel> tutorSearchModels, rv_interface Rv_interface)
    {
        this.context = context;
        this.Rv_interface = Rv_interface;
        this.tutorSearchModels = tutorSearchModels;
    }


    @NonNull
    @Override
    public rv_tutor_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_search_row,parent,false);



        return new rv_tutor_adapter.MyViewHolder(view, Rv_interface);
    }

    @Override
    public void onBindViewHolder(@NonNull rv_tutor_adapter.MyViewHolder holder, int position)
    {
        holder.Name.setText(tutorSearchModels.get(position).getName());
        holder.Rating.setText(tutorSearchModels.get(position).getRating());
        holder.Location.setText(tutorSearchModels.get(position).getLocation());

    }

    @Override
    public int getItemCount()
    {
        return tutorSearchModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        ImageView imageView;
        TextView Name;
        TextView Rating;
        TextView Location;

        public MyViewHolder(@NonNull View itemView, rv_interface Rv_interface)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSearchProfPic);
            Name = itemView.findViewById(R.id.textSearchName);
            Rating = itemView.findViewById(R.id.textSearchRating);
            Location = itemView.findViewById(R.id.textSearchLocation);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(Rv_interface!=null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            Rv_interface.onClick(position);
                        }
                    }

                }
            });
        }
    }
}
