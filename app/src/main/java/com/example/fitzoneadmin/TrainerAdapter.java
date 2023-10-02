package com.example.fitzoneadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrainerAdapter extends RecyclerView.Adapter<TrainerAdapter.ViewHolder> {

    private List<TrainerItem> trainerItems;
    private Context context;

    public TrainerAdapter(Context context, List<TrainerItem> trainerItems) {
        this.context = context;
        this.trainerItems = trainerItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trainer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrainerItem item = trainerItems.get(position);
        holder.trainerNameTextView.setText(item.getTrainerName());
        holder.trainerImageView.setImageResource(item.getTrainerImageResourceId());
    }

    @Override
    public int getItemCount() {
        return trainerItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView trainerNameTextView;
        ImageView trainerImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            trainerNameTextView = itemView.findViewById(R.id.trainerNameTextView);
            trainerImageView = itemView.findViewById(R.id.trainerImageView);
        }
    }
}