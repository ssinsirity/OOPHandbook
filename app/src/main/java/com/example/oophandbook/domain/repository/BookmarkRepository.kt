package com.example.oophandbook.domain.repository

import com.example.oophandbook.domain.model.BookmarkedUnit
import com.example.oophandbook.domain.model.UnitWithContent
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun observeBookmarked(): Flow<List<BookmarkedUnit>>
    suspend fun toggleBookmark(unitId: Int)
}