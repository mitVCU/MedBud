package com.mittens.medbud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.mittens.medbud.adapter.MedicineListAdapter;
import com.mittens.medbud.data.Manager;
import com.mittens.medbud.data.Mapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MedicineListActivity extends AppCompatActivity {
    private static final String TAG = "MedicineListActivity";
    @Bind(R.id.medicine_list)
    RecyclerView medicineList;
    private RecyclerView.Adapter<MedicineListAdapter.ViewHolder> adapter;
    private ArrayList<Mapper> medications;
    private List<Mapper> result;
    private Mapper medicine1;
    private Mapper medicine2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_medicine_list);

        ButterKnife.bind(this);

        medications = new ArrayList<>();


        //////////////////

        try {
            run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Mapper tests : result) {

            medications.add(tests);
        }

        //////////////////

        medicineList.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MedicineListAdapter(medications, getApplicationContext());
        medicineList.setAdapter(adapter);
    }


    public void run() throws InterruptedException {
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                Manager manager = new Manager();
                CognitoCachingCredentialsProvider credentialsProvider = Manager.getcredentials(MedicineListActivity.this);
                if (credentialsProvider != null) {

                    DynamoDBMapper dynamoDBMapper = manager.initDynamoClient(credentialsProvider);
                    //     medicine1 = dynamoDBMapper.load(Mapper.class, "advil");
                    DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

                    result = dynamoDBMapper.scan(Mapper.class, scanExpression);
                }
            }
        });

        b.start();
        b.join();
    }
}
