package com.example.oophandbook.domain.repository

import com.example.oophandbook.domain.model.Topic
import kotlinx.coroutines.flow.Flow

interface TopicRepository {
    fun observeTopics(): Flow<List<Topic>>
    suspend fun getById(id: Int): Topic?
}