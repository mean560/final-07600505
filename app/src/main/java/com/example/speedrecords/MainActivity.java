package com.example.speedrecords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.speedrecords.adapter.RecordAdapter;
import com.example.speedrecords.db.AppDatabase;
import com.example.speedrecords.model.Record;
import com.example.speedrecords.unit.AppExecutors;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    @Override
    protected void onResume(){
        super.onResume();

        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                final Record[] records = db.recordDao().getAllRecord();

                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        RecordAdapter adapter = new RecordAdapter(MainActivity.this, records);
                        mRecyclerView.setAdapter(adapter);
                    }
                });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.record_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        Button addrecordButton = findViewById(R.id.add_record_button);
        addrecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddRecordActivity.class);
                startActivity(i);
            }
        });
    }

}