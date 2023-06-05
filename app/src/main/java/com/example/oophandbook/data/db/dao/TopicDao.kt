package com.example.oophandbook.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.oophandbook.data.db.model.TopicEntity
import com.example.oophandbook.data.db.model.relation.TopicWithUnitsER
import kotlinx.coroutines.flow.Flow

@Dao
interface TopicDao : BaseDao<TopicEntity> {
    @Transaction
    @Query("SELECT * FROM Topic WHERE id = :id")
    suspend fun selectById(id: Int): TopicWithUnitsER?

    @Transaction
    @Query("SELECT * FROM Topic")
    fun observeAll(): Flow<List<TopicWithUnitsER>>
}