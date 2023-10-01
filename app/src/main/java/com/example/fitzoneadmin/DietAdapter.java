package com.example.fitzoneadmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.ViewHolder> {

    private List<ListItem2> mDataList;

    public DietAdapter(List<ListItem2> dataList) {
        this.mDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dedign2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem2 item = mDataList.get(position);
        holder.titleTextView.setText(item.getTitle());
        holder.setImage(item.getImageId());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(androidx.core.R.id.title);
            imageView = itemView.findViewById(R.id.imgId);
        }

        public void setImage(Integer resourceId) {
            // Set the image to imageView using the provided resourceId
            // For example: imageView.setImageResource(resourceId);
        }
    }
}
