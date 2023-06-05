package com.example.oophandbook.ui.screen.bookmark

import com.example.oophandbook.domain.model.Topic
import com.example.oophandbook.domain.model.UnitBase

sealed interface BookmarkUiState {
    object Loading : BookmarkUiState
    object NoBookmarks : BookmarkUiState

    data class Content(val bookmarks: List<Pair<Topic, List<UnitBase>>>) : BookmarkUiState
}