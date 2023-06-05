package com.example.oophandbook.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.oophandbook.data.db.model.BookmarkEntity
import com.example.oophandbook.data.db.model.relation.BookmarkAndUnitER
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao : BaseDao<BookmarkEntity> {
    @Transaction
    @Query("SELECT * FROM Bookmark")
    fun observeAll(): Flow<List<BookmarkAndUnitER>>

    @Transaction
    @Query("SELECT * FROM Bookmark WHERE unit_id = :unitId")
    suspend fun selectByUnitId(unitId: Int): BookmarkAndUnitER?

    @Query("DELETE FROM Bookmark WHERE unit_id = :unitId")
    suspend fun deleteByUnitId(unitId: Int)
}