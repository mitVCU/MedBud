package com.mittens.medbud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.mittens.medbud.data.Manager;
import com.mittens.medbud.data.Mapper;

public class SeeUserActivity extends AppCompatActivity {


    private static final String TAG = "HER";
    Integer medicationFrequency;
    String medicationTime;
    String medicationName;
    String medicationQuantity;
    Mapper mapper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_see_user);

        TextView name = (TextView) findViewById(R.id.firstNameST);
        TextView lastName = (TextView) findViewById(R.id.lastNameST);
        TextView ID = (TextView) findViewById(R.id.IDST);
        Button submit;


        medicationName = mapper.getMedicationName();

        name.setText(medicationName);
        lastName.setText(mapper.getMedicationTime());
        ID.setText(mapper.getMedicationFrequency());

        submit = (Button) findViewById(R.id.submit_ar);


    }

    public void run() throws InterruptedException {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                Manager manager = new Manager();
                CognitoCachingCredentialsProvider credentialsProvider = Manager.getcredentials(SeeUserActivity.this);
                if (credentialsProvider != null) {

                    DynamoDBMapper dynamoDBMapper = manager.initDynamoClient(credentialsProvider);
                    mapper = dynamoDBMapper.load(Mapper.class, "red");
                }
            }
        });

        a.start();
        a.join();
    }


}
