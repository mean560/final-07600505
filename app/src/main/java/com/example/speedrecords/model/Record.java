package com.example.speedrecords.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "records")
public class Record {

    @PrimaryKey(autoGenerate = true)
    public final int id;

    public  final double sum;

    public final double distance;

    public final double duration;


    public Record(int id, double sum, double distance, double duration) {
        this.id = id;
        this.sum = sum;
        this.distance = distance;
        this.duration = duration;
    }
}
