package com.example.fitzoneadmin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

    private List<WorkoutItem> workoutItems;
    private Context context;
    private WorkoutAdapter.OnItemClickListener onItemClickListener;

    public WorkoutAdapter(Context context, List<WorkoutItem> workoutItems) {
        this.context = context;
        this.workoutItems = workoutItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WorkoutItem currentItem = workoutItems.get(position);

        if (currentItem != null) {
            String workoutName = currentItem.getWorkoutName();
            String workoutFocusArea = currentItem.getWorkoutFocusArea();
            String workoutDescription = currentItem.getWorkoutDescription();

            // Check if the values are not null before using them
            if (workoutName != null) {
                holder.workoutNameTextView.setText(workoutName);
            } else {
                holder.workoutNameTextView.setText("");
            }

            if (workoutFocusArea != null) {
                holder.focusAreaTextView.setText(workoutFocusArea);
            } else {
                holder.focusAreaTextView.setText("");
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, WorkoutData.class);

                    // Add the FLAG_ACTIVITY_NEW_TASK flag
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.startActivity(intent);
                }
            });

        }

//        WorkoutItem item = workoutItems.get(position);
//        holder.workoutNameTextView.setText(item.getWorkoutName());
//        holder.focusAreaTextView.setText(item.getWorkoutFocusArea());
//        holder.workoutImageView.setImageResource(Integer.parseInt(item.getWorkoutImageResourceId()));
    }

    @Override
    public int getItemCount() {
        return workoutItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView workoutNameTextView;
        TextView focusAreaTextView;
        ImageView workoutImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            workoutNameTextView = itemView.findViewById(R.id.workoutNameTextView);
            focusAreaTextView = itemView.findViewById(R.id.focusAreaTextView);
            workoutImageView = itemView.findViewById(R.id.workoutImageView);


        }
    }

    public class OnItemClickListener {
    }
}