package com.mittens.medbud;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class Main2Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<LauncherActivity.ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        // dummy data

        for (int i = 0; i <= 10; i++) {
            LauncherActivity.ListItem listItem = new LauncherActivity.ListItem
                    ("heading" + (i + 1), "lorem something IDC");

            listItems.add(listItem);


        }

        adapter = new MyAdapter(listItems, this);

        recyclerView.setAdapter(adapter);
    }
}
