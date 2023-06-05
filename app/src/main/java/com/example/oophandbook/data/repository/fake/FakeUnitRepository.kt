package com.example.oophandbook.data.repository.fake

import com.example.oophandbook.domain.model.Content
import com.example.oophandbook.domain.model.UnitBase
import com.example.oophandbook.domain.model.UnitWithContent
import com.example.oophandbook.domain.repository.UnitRepository
import javax.inject.Inject

class FakeUnitRepository @Inject constructor() : UnitRepository {
    private val numberOfTopics = 10
    private val numberOfUnits = 5

    private val units = List(numberOfTopics * numberOfUnits) { i ->
        val topicIdx = i % numberOfTopics
        val unitIdx = topicIdx % numberOfUnits

        UnitWithContent(
            id = unitIdx,
            topicId = topicIdx,
            position = unitIdx,
            name = "Unit #$unitIdx",
            content = List(10) { idx ->
                if (idx % 2 == 0)
                    Content.Text(
                        id = idx,
                        position = idx,
                        text = "This is a text #$idx"
                    )
                else
                    Content.Code(
                        id = idx,
                        position = idx,
                        code = "This is a code #$idx"
                    )
            }
        )
    }

    override suspend fun getById(id: Int): UnitWithContent? = units.find { it.id == id }

    override suspend fun getByPosition(topicId: Int, position: Int): UnitBase? {
        val unit = units.find { it.topicId == topicId && it.position == position }

        return unit?.let {
            UnitBase(
                id = it.id,
                name = it.name,
                position = it.position
            )
        }
    }
}