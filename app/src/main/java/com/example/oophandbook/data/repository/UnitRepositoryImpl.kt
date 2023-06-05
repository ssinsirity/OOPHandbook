package com.example.oophandbook.data.repository

import com.example.oophandbook.data.db.dao.UnitDao
import com.example.oophandbook.data.mapper.toUnit
import com.example.oophandbook.domain.model.UnitBase
import com.example.oophandbook.domain.model.UnitWithContent
import com.example.oophandbook.domain.repository.UnitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UnitRepositoryImpl @Inject constructor(private val unitDao: UnitDao) : UnitRepository {

    override suspend fun getById(id: Int): UnitWithContent? = withContext(Dispatchers.IO) {
        val unitEntity = unitDao.selectById(id) ?: return@withContext null
        val unit = unitEntity.toUnit()
        val sortedContent = unit.content.sortedBy { it.position }
        unit.copy(content = sortedContent)
    }

    override suspend fun getByPosition(topicId: Int, position: Int): UnitBase? =
        withContext(Dispatchers.IO) {
            unitDao.selectByPosition(topicId, position)?.toUnit()
        }
}