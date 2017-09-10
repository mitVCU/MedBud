package com.mittens.medbud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mittens.medbud.adapter.MedicineListAdapter;
import com.mittens.medbud.data.Mapper;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MedicineListActivity extends AppCompatActivity {
    @Bind(R.id.medicine_list)
    RecyclerView medicineList;

    private RecyclerView.Adapter<MedicineListAdapter.ViewHolder> adapter;
    private ArrayList<Mapper> medications;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_medicine_list);

        ButterKnife.bind(this);

        medications = new ArrayList<>();

        Mapper medicine1 = new Mapper();
        Mapper medicine2 = new Mapper();

        medications.add(medicine1);
        medications.add(medicine2);

        medicineList.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MedicineListAdapter(medications, getApplicationContext());
        medicineList.setAdapter(adapter);
    }
}
