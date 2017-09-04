package com.example.marosu.secretchat.model.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Marius-Andrei Rosu on 8/30/2017.
 */
@Entity(tableName = "converation")
data class ConversationField(@PrimaryKey val id: String,
                             @ColumnInfo(name = "createdDtm") val timestamp: Long,
                             @ColumnInfo(name = "content") val content: ConversationContent)
