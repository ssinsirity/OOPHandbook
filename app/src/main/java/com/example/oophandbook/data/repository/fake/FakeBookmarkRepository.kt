package com.example.oophandbook.data.repository.fake

import com.example.oophandbook.domain.model.BookmarkedUnit
import com.example.oophandbook.domain.model.UnitWithContent
import com.example.oophandbook.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class FakeBookmarkRepository @Inject constructor() : BookmarkRepository {
    /*private val bookmarks = List(10) { i ->
        val idx = i % 5

        BookmarkedUnit(
            id = i,
            unit = UnitWithContent(
                id = i,
                topicId = i,
                position = idx,
                name = "Unit #$idx",
                content = emptyList()
            )
        )
    }
    private val bookmarksFlow = MutableStateFlow(bookmarks)*/

    override fun observeBookmarked(): Flow<List<BookmarkedUnit>> = TODO()/*bookmarksFlow*/

    override suspend fun toggleBookmark(unitId: Int) {
        /*bookmarksFlow.update { bookmarks ->
            val foundBookmark = bookmarks.find { it.unit.id == unitId }

            if (foundBookmark == null) {
                val bookmark = BookmarkedUnit(
                    id = (10..100).random(),
                    unit = UnitWithContent(
                        id = unitId,
                        topicId = (0..10).random(),
                        position = (0..5).random(),
                        name = "Unit #$unitId",
                        content = emptyList()
                    )
                )
                bookmarks + bookmark
            } else
                bookmarks - foundBookmark
        }*/
        TODO()
    }
}