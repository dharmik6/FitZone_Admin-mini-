package com.example.fitzoneadmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

    private List<ListItem> mDataList;

    public WorkoutAdapter(List<ListItem> dataList) {
        this.mDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_disign, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem item = mDataList.get(position);
        holder.titleTextView.setText(item.getTitle());
        holder.subTitleTextView.setText(item.getSubTitle());
        // Assuming you have a method in your ViewHolder class to set the image
        holder.setImage(item.getImagId());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView subTitleTextView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title);
            subTitleTextView = itemView.findViewById(R.id.sub_title);
            imageView = itemView.findViewById(R.id.imgId);
        }

        public void setImage(Integer resourceId) {
            // Set the image to imageView using the provided resourceId
            // For example: imageView.setImageResource(resourceId);
        }
    }
}
