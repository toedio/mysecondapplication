package com.example.mysecondapplication.repository;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(tableName = "person")
public class Person {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "age")
    private String age;

    @ColumnInfo(name = "syncronized")
    private Boolean syncronized;

    @ColumnInfo(name = "createdAt")
    private Long createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Boolean getSyncronized() {
        return syncronized;
    }

    public void setSyncronized(Boolean syncronized) {
        this.syncronized = syncronized;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Person() {

    }

    public Person(String name, String age, Boolean syncronized) {
        this.name = name;
        this.age = age;
        this.syncronized = syncronized;
        this.createdAt = new Date().getTime();
    }
}
