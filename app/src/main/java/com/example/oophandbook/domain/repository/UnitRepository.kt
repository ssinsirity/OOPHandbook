package com.example.oophandbook.domain.repository

import com.example.oophandbook.domain.model.UnitBase
import com.example.oophandbook.domain.model.UnitWithContent

interface UnitRepository {
    suspend fun getById(id: Int): UnitWithContent?
    suspend fun getByPosition(topicId: Int, position: Int): UnitBase?
}