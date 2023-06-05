package com.example.oophandbook.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.oophandbook.data.db.model.UnitEntity
import com.example.oophandbook.data.db.model.relation.UnitWithContentER

@Dao
interface UnitDao : BaseDao<UnitEntity> {
    @Transaction
    @Query("SELECT * FROM Unit WHERE id = :id")
    suspend fun selectById(id: Int): UnitWithContentER?

    /*@Transaction
    @Query("SELECT * FROM Unit WHERE topic_id = :id")
    suspend fun selectByTopicId(id: Int): List<UnitWithContentER>*/

    @Query("SELECT * FROM Unit WHERE topic_id = :topicId AND number = :position")
    suspend fun selectByPosition(topicId: Int, position: Int): UnitEntity?
}