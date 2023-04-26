package com.example.ccn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

    ArrayList<Model> mlist;
    Context context;

    public FeedAdapter(Context context, ArrayList<Model> mList) {
        this.mlist = mlist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.post, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = mlist.get(position);
        holder.user.setText(model.getUser());
        holder.location.setText(model.getLocation());
        holder.preview.setText(model.getPreview());
        holder.subject.setText(model.getSubject());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView user, subject, location, preview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            user = itemView.findViewById(R.id.user_text);
            subject = itemView.findViewById(R.id.subject_text);
            location = itemView.findViewById(R.id.location_text);
            preview = itemView.findViewById(R.id.preview_text);
        }
    }
}
