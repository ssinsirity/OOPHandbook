package com.example.oophandbook.ui.screen.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oophandbook.domain.model.UnitBase
import com.example.oophandbook.domain.repository.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {

    val uiState = bookmarkRepository.observeBookmarked()
        .map { b -> b.groupBy(keySelector = { it.topic }, valueTransform = { it.unit }).toList() }
        .map { if (it.isEmpty()) BookmarkUiState.NoBookmarks else BookmarkUiState.Content(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), BookmarkUiState.Loading)

    fun removeUnit(unit: UnitBase) {
        viewModelScope.launch {
            bookmarkRepository.toggleBookmark(unit.id)
        }
    }
}