package com.example.marosu.secretchat.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.marosu.secretchat.model.db.entity.Message;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by Marius-Andrei Rosu on 8/21/2017.
 */
@Dao
public interface MessageDao {
    @Query("SELECT * FROM message")
    Maybe<List<Message>> getAll();

    @Insert
    void insert(Message message);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertAll(List<Message> messages);
}
