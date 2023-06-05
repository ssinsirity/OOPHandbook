package com.example.oophandbook.ui.screen.home

import com.example.oophandbook.domain.model.Topic

sealed interface HomeUiState {
    object Loading : HomeUiState

    data class Content(val topics: List<Topic>) : HomeUiState
}