package com.example.marosu.secretchat.model.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
@Entity(tableName = "conversation")
public class Conversation {
    @PrimaryKey
    private String id;

    @ColumnInfo(name = "createdDtm")
    @SerializedName("createdDtm")
    private long timestamp;

    public Conversation(String id, long timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
