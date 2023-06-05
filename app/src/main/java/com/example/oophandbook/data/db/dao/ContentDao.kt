package com.example.oophandbook.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.oophandbook.data.db.model.ContentEntity

@Dao
interface ContentDao : BaseDao<ContentEntity> {
    @Query("SELECT * FROM Content WHERE unit_id = :id")
    suspend fun selectByUnitId(id: Int): List<ContentEntity>
}