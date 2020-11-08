package com.example.speedrecords.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.speedrecords.model.Record;

@Dao
public interface RecordDao {
    @Query("SELECT * FROM records")
    Record[] getAllRecord();

    @Query("SELECT * FROM records WHERE id = :id")
    Record getRecordById(int id);

    @Insert
    void addRecord(Record... record);
}
