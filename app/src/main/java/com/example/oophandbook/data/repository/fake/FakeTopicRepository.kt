package com.example.oophandbook.data.repository.fake

import com.example.oophandbook.domain.model.Topic
import com.example.oophandbook.domain.model.UnitBase
import com.example.oophandbook.domain.repository.TopicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class FakeTopicRepository @Inject constructor() : TopicRepository {
    private val topics = List(10) { idx ->
        Topic(
            id = idx,
            name = "Topic $idx",
            units = List(5) { unitIdx ->
                UnitBase(
                    id = unitIdx,
                    name = "Unit $unitIdx",
                    position = unitIdx
                )
            }
        )
    }
    private val topicsFlow = MutableStateFlow(topics)

    override fun observeTopics(): Flow<List<Topic>> = topicsFlow

    override suspend fun getById(id: Int): Topic? = topics.find { it.id == id }
}