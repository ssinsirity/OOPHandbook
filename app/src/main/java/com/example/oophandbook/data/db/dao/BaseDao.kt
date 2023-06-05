package com.example.oophandbook.data.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Upsert

interface BaseDao<T> {
    @Insert
    suspend fun insert(obj: T): Long

    @Insert
    suspend fun insert(vararg obj: T): List<Long>

    @Upsert
    suspend fun upsert(obj: T)

    @Upsert
    suspend fun upsert(vararg obj: T)

    @Update
    suspend fun update(obj: T)

    @Delete
    suspend fun delete(obj: T)
}