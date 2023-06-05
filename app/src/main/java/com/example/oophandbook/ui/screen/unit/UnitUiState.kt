package com.example.oophandbook.ui.screen.unit

import com.example.oophandbook.domain.model.UnitBase
import com.example.oophandbook.domain.model.UnitWithContent

sealed interface UnitUiState {
    object Loading : UnitUiState

    object UnitError : UnitUiState

    data class Content(
        val unit: UnitWithContent,
        val isBookmarked: Boolean = false,
        val previous: UnitBase? = null,
        val next: UnitBase? = null,
    ) : UnitUiState
}