package com.example.marosu.secretchat.model.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Marius-Andrei Rosu on 8/23/2017.
 */
@Entity(tableName = "participant")
public class Participant {
    @PrimaryKey(autoGenerate = true)
    private String id;

    @ColumnInfo(name = "user_id")
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
