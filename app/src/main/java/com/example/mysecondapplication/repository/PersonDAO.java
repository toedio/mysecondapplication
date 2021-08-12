package com.example.mysecondapplication.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDAO {

    @Query("SELECT COUNT(1) FROM person")
    Long count();

    @Query("SELECT COUNT(1) FROM person WHERE syncronized = :sync")
    Long countBySync(Boolean sync);

    @Query("SELECT * FROM person")
    List<Person> getAll();

    @Query("SELECT * FROM person WHERE syncronized = :sync")
    List<Person> getBySync(Boolean sync);

    @Insert
    Long save(Person person);

    @Update
    void update(Person person);
}
