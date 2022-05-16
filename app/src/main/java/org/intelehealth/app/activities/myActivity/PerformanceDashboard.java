package org.intelehealth.app.activities.myActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.intelehealth.app.R;
import org.intelehealth.app.activities.homeActivity.HomeActivity;
import org.intelehealth.app.activities.searchPatientActivity.SearchPatientActivity;

public class PerformanceDashboard extends AppCompatActivity {
    CardView c2, c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_dashboard);
        c2 = findViewById(R.id.cardview_doctorlist);
        c3 = findViewById(R.id.cardview_health_worker);

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerformanceDashboard.this, DoctorList.class);
                startActivity(intent);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerformanceDashboard.this, NurseList.class);
                startActivity(intent);
            }
        });

    }
}