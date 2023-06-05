package com.example.oophandbook.data.repository

import com.example.oophandbook.data.db.dao.TopicDao
import com.example.oophandbook.data.mapper.toUnit
import com.example.oophandbook.data.mapper.toDtos
import com.example.oophandbook.domain.model.Topic
import com.example.oophandbook.domain.repository.TopicRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TopicRepositoryImpl @Inject constructor(private val topicDao: TopicDao) : TopicRepository {

    override fun observeTopics(): Flow<List<Topic>> =
        topicDao.observeAll().map { it.toDtos() }.flowOn(Dispatchers.IO)

    override suspend fun getById(id: Int): Topic? = withContext(Dispatchers.IO) {
        topicDao.selectById(id)?.toUnit()
    }
}