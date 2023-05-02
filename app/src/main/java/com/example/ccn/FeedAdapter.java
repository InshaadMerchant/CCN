package com.example.ccn;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {
    private final PostInterface postInterface;
    ArrayList<Model> mlist;
    Context context;

    public void setFilteredList(ArrayList<Model> filterList){
        this.mlist = filterList;
        notifyDataSetChanged();
    }

    public FeedAdapter(Context context, ArrayList<Model> mlist, PostInterface postInterface) {
        this.mlist = mlist;
        this.context = context;
        this.postInterface = postInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.post, parent, false);
        return new MyViewHolder(v, postInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = mlist.get(position);
        holder.title.setText(model.getTitle());
        holder.contents.setText(model.getContents());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView title;
        Button contents;


        public MyViewHolder(@NonNull View itemView, PostInterface postInterface) {
            super(itemView);

            title = itemView.findViewById(R.id.title_text);
            contents = itemView.findViewById(R.id.contents);


            contents.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(postInterface != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            postInterface.onButtonClick(position);
                        }
                    }
                }
            });
        }
    }
}

