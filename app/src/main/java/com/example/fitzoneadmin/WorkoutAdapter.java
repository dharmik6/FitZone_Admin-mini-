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

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

    private List<WorkoutItem> workoutItems;
    private Context context;

    public WorkoutAdapter(Context context, List<WorkoutItem> workoutItems) {
        this.context = context;
        this.workoutItems = workoutItems;

    }

    @NonNull
    @Override
    public WorkoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workout, parent, false);
        return new WorkoutAdapter.ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull WorkoutAdapter.ViewHolder holder, int position) {
        WorkoutItem currentItem = workoutItems.get(position);
        if (currentItem != null) {
            String workName = currentItem.getWorkoutName();
            String workoutFocusArea = currentItem.getWorkoutFocusArea();
            String imageUrl = currentItem.getImageUrl();
            String workDescription = currentItem.getWorkoutDescription();

            // Check if the values are not null before using them
            if (workName != null) {
                holder.workoutNameTextView.setText(workName);
            } else {
                holder.workoutNameTextView.setText("");
            }
            if (workoutFocusArea != null) {
                holder.focusAreaTextView.setText(workoutFocusArea);
            } else {
                holder.focusAreaTextView.setText("");
            }
            // Check if the values are not null before using them
            if (workDescription != null) {
            } else {
            }
            // Load the image using Picasso
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Picasso.get().load(imageUrl).into(holder.workoutImageView);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, WorkoutData.class);

                    intent.putExtra("wname", workName);
                    intent.putExtra("focus", workoutFocusArea);
                    intent.putExtra("desc", workDescription);
                    intent.putExtra("imag", imageUrl);

                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return workoutItems.size();
    }
   //************************************
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
}