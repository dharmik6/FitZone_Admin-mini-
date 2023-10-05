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
    public void onBindViewHolder(@NonNull TrainerAdapter.ViewHolder holder, int position) {
        TrainerItem currentItem = trainerItems.get(position);
        holder.trainerNameTextView.setText(currentItem.getTrainerName());

        // Load trainer image from Firebase using Glide
        String imageUrl = currentItem.getTrainerImageResourceId();
        Glide.with(context)
                .load(imageUrl)
                .into(holder.trainerImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                TrainerItem item = trainerItems.get(position);
                if (position != RecyclerView.NO_POSITION) {
                    // Get trainer data
                    String trainername = item.getTrainerName();
                    String email = item.getEmail();
                    String number = item.getNumber();
                    String age = item.getAge();
                    String gender = item.getGender();

                    // Open TrainerData activity and pass the data
                    Intent intent = new Intent(context, TrainerData.class);
                    intent.putExtra("trainername", trainername);
                    intent.putExtra("email", email);
                    intent.putExtra("number", number);
                    intent.putExtra("age", age);
                    intent.putExtra("gender", gender);
                    intent.putExtra("trainerimage", imageUrl);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else {
                    // Handle the case where the position is invalid or the view holder is detached.
                    // You can log an error or display a message to the user.
                }
            }
        });
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
