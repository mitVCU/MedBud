//package com.mittens.medbud;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.amazonaws.auth.CognitoCachingCredentialsProvider;
//import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
//import com.mittens.medbud.data.Manager;
//import com.mittens.medbud.data.Mapper;
//
//public class EnterUserActivity extends AppCompatActivity {
//
//    EditText name;
//    EditText lastName;
//    EditText ID;
//    Button submit;
//
//    String medicationFrequency;
//    String medicationTime;
//    String medicationName;
//    String medicationQuantity;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_enter_user);
//
//        name = (EditText) findViewById(R.id.firstNameET);
//        lastName = (EditText) findViewById(R.id.lastNameET);
//        ID = (EditText) findViewById(R.id.ID);
//
//        submit = (Button) findViewById(R.id.submit_btn);
//        medicationName = name.getText().toString();
//        medicationTime = lastName.getText().toString();
//
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new updatetable().execute();
//            }
//        });
//
//    }
//
//
//    private class updatetable extends AsyncTask<Void, Integer, Integer> {
//        @Override
//        protected Integer doInBackground(Void... parma) {
//
////            Manager manager = new Manager();
////            CognitoCachingCredentialsProvider credentialsProvider = Manager.getcredentials(EnterUserActivity.this);
////
////            Mapper mapper = new Mapper();
////            mapper.setMedicationFrequency(medicationFrequency);
////            mapper.setMedicationName(medicationName);
////            mapper.setMedicationQuantity(medicationQuantity);
////            mapper.setMedicationTime(medicationTime);
////
////            if (credentialsProvider != null && mapper != null) {
////                DynamoDBMapper dynamoDBMapper = manager.initDynamoClient(credentialsProvider);
////                dynamoDBMapper.save(mapper);
////            } else {
////                return 2;
////            }
////            return 1;
//
//        //}
////
////        @Override
////        protected void onPostExecute(Integer integer) {
////            super.onPostExecute(integer);
////            if (integer == 1) {
////                Toast.makeText(EnterUserActivity.this, "fuck Yes!!!!!", Toast.LENGTH_SHORT).show();
////
////            } else if (integer == 2) {
////                Toast.makeText(EnterUserActivity.this, "fuck nooo, db erro enteruseractivity..", Toast.LENGTH_SHORT).show();
////            }
//        }
//
//    }
//}
