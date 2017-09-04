package com.example.marosu.secretchat.model.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by Marius-Andrei Rosu on 8/30/2017.
 */
@Dao
public interface ConversationDao {
    @Query("SELECT * FROM conversation")
    Maybe<List<ConversationDao>> getAll();

    @Insert
    void insert(ConversationDao conversation);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertAll(List<ConversationDao> conversations);
}
