package com.example.speedrecords;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.speedrecords.db.AppDatabase;
import com.example.speedrecords.model.Record;
import com.example.speedrecords.unit.AppExecutors;

import java.util.Locale;

public class AddRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText meterEditText = findViewById(R.id.meters_edit_text);
                final String meterText = meterEditText.getText().toString();

                final EditText durationEditText = findViewById(R.id.seconds_edit_text);
                final String durationText = durationEditText.getText().toString();

                double meter = Double.parseDouble(meterText);
                double second = Double.parseDouble(durationText);

                double speed_sum = (meter*18)/(second*5);
                //String str = String.format(Locale.getDefault(), "%.1f", speed_sum);

                final Record record = new Record(0, speed_sum, meter, second);

                AppExecutors executors = new AppExecutors();
                executors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() { // worker thread
                        AppDatabase db = AppDatabase.getInstance(AddRecordActivity.this);
                        db.recordDao().addRecord(record);
                        finish();
                    }
                });

            }
        });

    }
}