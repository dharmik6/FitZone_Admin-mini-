package com.example.fitzoneadmin;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserList extends AppCompatActivity {
    //*********************************
    // RecyclerView
    RecyclerView user_list ;
    RecyclerView.Adapter adaptor;

    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        user_list = findViewById(R.id.user_recyclerView);
        user_list.setHasFixedSize(true);
        user_list.setLayoutManager(new LinearLayoutManager(this));

        List<ListItem> userArrayList = new ArrayList<>();
        userArrayList.add(new ListItem("dharmik", "kacha", R.drawable.my));
        userArrayList.add(new ListItem("dharmik", "kacha", R.drawable.my));

        // Add more items as needed

        adapter = new MyAdapter(this, userArrayList);
        user_list.setAdapter(adapter);


        //**********************************
        //back page button
        ImageView back_page = findViewById(R.id.btn_next_page);
        back_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

}