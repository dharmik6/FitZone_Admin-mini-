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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<UserItem> userItems;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private int adapterPosition;

    public UserAdapter(Context context, List<UserItem> userItems) {
        this.context = context;
        this.userItems = userItems;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserItem item = userItems.get(position);
        holder.userNameTextView.setText(item.getUserName());

        // Load user image from Firebase using Glide
        String imageUrl = item.getUserImageResourceId(); // Assuming you have a method to get the image URL from your UserItem class
        Glide.with(context)
                .load(imageUrl) // Load image URL
                .into(holder.userImageView); // Set the loaded image to the ImageView

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition(); // Use holder.getAdapterPosition() instead of getAdapterPosition()

                if (position != RecyclerView.NO_POSITION) {
                    UserItem item = userItems.get(position);

                    String username = item.getUserName();
                    String email = item.getEmail();
                    String age = item.getAge();
                    String number = item.getNumber();
                    String gender = item.getGender();
                    String height = item.getHiegth(); // Typo corrected from hiegth to height
                    String weight = item.getWiegth(); // Typo corrected from wirgth to weight
                    String imageUrl = item.getUserImageResourceId();

                    Intent intent = new Intent(context, UserData.class);
                    intent.putExtra("username", username);
                    intent.putExtra("email", email);
                    intent.putExtra("age", age);
                    intent.putExtra("number", number);
                    intent.putExtra("gender", gender);
                    intent.putExtra("height", height);
                    intent.putExtra("weight", weight);
                    intent.putExtra("userimage", imageUrl);

                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else {
                    // Handle the case where the position is invalid or the view holder is detached.
                    // You can log an error or display a message to the user.
                }
            }
        });
    }



    public void setAdapterPosition(int adapterPosition) {
        this.adapterPosition = adapterPosition;
    }

    public int getAdapterPosition() {
        return adapterPosition;
    }

    public interface OnItemClickListener {
        void onItemClick(int position); // Step 1: Define a method for item click
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener; // Step 2: Set the item click listener
    }




    @Override
    public int getItemCount() {
        if (userItems != null) {
            return userItems.size(); // Return the size of the list if it's not null
        } else {
            return 0; // Return 0 if the list is null
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTextView;
        ImageView userImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            userImageView = itemView.findViewById(R.id.userImageView);
        }
    }
}