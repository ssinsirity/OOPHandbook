package com.example.oophandbook.data.repository

import com.example.oophandbook.data.db.dao.BookmarkDao
import com.example.oophandbook.data.db.model.BookmarkEntity
import com.example.oophandbook.data.mapper.toDtos
import com.example.oophandbook.domain.model.BookmarkedUnit
import com.example.oophandbook.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(private val bookmarkDao: BookmarkDao) :
    BookmarkRepository {

    override fun observeBookmarked(): Flow<List<BookmarkedUnit>> =
        bookmarkDao.observeAll().map { it.toDtos() }

    override suspend fun toggleBookmark(unitId: Int) {
        if (bookmarkDao.selectByUnitId(unitId) == null)
            bookmarkDao.insert(BookmarkEntity(unitId = unitId))
        else
            bookmarkDao.deleteByUnitId(unitId)
    }
}