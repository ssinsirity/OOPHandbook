package com.example.oophandbook.ui.screen.unit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oophandbook.domain.repository.BookmarkRepository
import com.example.oophandbook.domain.repository.UnitRepository
import com.example.oophandbook.ui.navigation.UnitNavArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UnitViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val unitRepository: UnitRepository,
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {

    private val args = UnitNavArgs.from(savedStateHandle)
    private val unitId = flowOf(args.unitId)

    private val unit = unitId.map(unitRepository::getById)
    private val bookmarkedUnits = bookmarkRepository.observeBookmarked()

    val uiState = combine(unit, bookmarkedUnits) { unit, bookmarkedUnits ->
        if (unit == null)
            return@combine UnitUiState.UnitError

        val previous = unitRepository.getByPosition(unit.topicId, unit.position - 1)
        val next = unitRepository.getByPosition(unit.topicId, unit.position + 1)

        UnitUiState.Content(
            unit = unit,
            previous = previous,
            next = next,
            isBookmarked = bookmarkedUnits.any { it.unit.id == unit.id }
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UnitUiState.Loading)

    fun toggleBookmark() {
        viewModelScope.launch {
            bookmarkRepository.toggleBookmark(args.unitId)
        }
    }
}