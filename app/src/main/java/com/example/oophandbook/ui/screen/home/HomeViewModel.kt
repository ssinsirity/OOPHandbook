package com.example.oophandbook.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oophandbook.domain.model.Topic
import com.example.oophandbook.domain.repository.TopicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    topicRepository: TopicRepository
) : ViewModel() {

    private val query = MutableStateFlow("")
    private val topics = topicRepository.observeTopics()

    val uiState = combine(topics, query) { topics, query ->
        HomeUiState.Content(filterTopics(topics, query))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeUiState.Loading)

    fun updateQuery(value: String) {
        query.update { value }
    }

    private fun filterTopics(topics: List<Topic>, query: String) =
        topics.filter { topic ->
            topic.name.contains(query, true)
                    || topic.units.any { unit -> unit.name.contains(query, true) }
        }.map { topic ->
            val filteredUnits = topic.units.filter { it.name.contains(query, true) }
            topic.copy(units = filteredUnits)
        }
}