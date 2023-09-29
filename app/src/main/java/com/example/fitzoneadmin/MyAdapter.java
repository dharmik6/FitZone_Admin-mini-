package com.example.fitzoneadmin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public MyAdapter(Context context, List<ListItem> listItems){
        this.context = context;
        this.listItems = listItems;
    }

    public MyAdapter(List<ListItem> userArrayList) {
        this.context = context;
        this.listItems = listItems;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_disign, parent, false);
        return new ViewHolder(view);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener; // Step 2: Set the item click listener
    }

    public interface OnItemClickListener {
        void onItemClick(int position); // Step 1: Define a method for item click
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem item = listItems.get(position);
        holder.name.setText(item.getTitle());
        holder.phone.setText(item.getSubTitle());
//        holder.imageView.setImageResource(item.getImagId());
    }

    @Override
    public int getItemCount() {
        if (listItems != null) {
            return listItems.size(); // Return the size of the list if it's not null
        } else {
            return 0; // Return 0 if the list is null
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView phone;
//        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title);
            phone = itemView.findViewById(R.id.sub_title);
//            imageView = itemView.findViewById(R.id.imgId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, UserData.class);
                    context.startActivity(intent);
//                    Toast.makeText(context, name+"", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
