package com.mittens.medbud;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    CalendarView calendar;
    Long date;
    private TextView theDate;
    private Button btnGoCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        btnGoCalendar.setOnClickListener(new View.OnClickListener()){
//            @Override
//            public void onClick(View view){
//                Intent intent = new Intent (MainActivity.this, MedicineListActivity.class);
//                startActivity(intent);
//            }
//        };

        calendar = (CalendarView) findViewById(R.id.calendarView);
        date = calendar.getDate();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int currentapiVersion = android.os.Build.VERSION.SDK_INT;

                if (currentapiVersion >= Build.VERSION_CODES.LOLLIPOP) {
                    // Do something for lollipop and above versions

                    Intent myIntent = new Intent(MainActivity.this, MedicineListActivity.class);
                    myIntent.putExtra("day", dayOfMonth).putExtra("month", month + 1).putExtra("year", year);
                    startActivity(myIntent);
                } else {
                    // do something for phones running an SDK before lollipop

                /*With this if i block onSelectedDayChange when the mounth change*/
                    if (calendar.getDate() != date) {
                        date = calendar.getDate();

                        Intent myIntent = new Intent(MainActivity.this, MedicineListActivity.class);
                        myIntent.putExtra("day", dayOfMonth).putExtra("month", month + 1).putExtra("year", year);
                        startActivity(myIntent);
                    }
                }
            }
        });
    }
}
