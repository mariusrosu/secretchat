package com.example.marosu.secretchat.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.marosu.secretchat.model.db.entity.Conversation;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by Marius-Andrei Rosu on 8/23/2017.
 */
@Dao
public interface ConversationDao {
    @Query("SELECT * FROM conversation")
    Maybe<List<Conversation>> getAll();

    @Insert
    void insert(Conversation conversation);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertAll(List<Conversation> conversations);
}
