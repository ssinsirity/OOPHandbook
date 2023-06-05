@file:OptIn(ExperimentalAnimationApi::class)

package com.example.oophandbook.ui.screen.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.oophandbook.domain.model.Topic
import com.example.oophandbook.domain.model.UnitBase
import com.example.oophandbook.ui.components.LoadingIndicator
import com.example.oophandbook.ui.components.SearchBar
import com.example.oophandbook.ui.components.rememberSearchState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToUnit: (Int) -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        HomeUiState.Loading -> LoadingIndicator()
        is HomeUiState.Content -> HomeScreen(
            state = state as HomeUiState.Content,
            onUnitClick = { onNavigateToUnit(it.id) },
            onQueryChange = viewModel::updateQuery
        )
    }
}

@Composable
fun HomeScreen(
    state: HomeUiState.Content,
    onUnitClick: (UnitBase) -> Unit = {},
    onQueryChange: (String) -> Unit = {}
) {
    var expandedTopicId by rememberSaveable { mutableStateOf(-1) }
    val searchState = rememberSearchState()

    LaunchedEffect(searchState.query) {
        onQueryChange(searchState.query.text)
    }
    LaunchedEffect(state.topics) {
        if (state.topics.none { it.id == expandedTopicId })
            expandedTopicId = -1
    }

    LazyColumn(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        item {
            SearchBar(
                query = searchState.query,
                onQueryChange = { searchState.query = it },
                onSearchFocusChange = { searchState.focused = it },
                onClearQuery = { searchState.query = TextFieldValue() },
                onBack = { searchState.query = TextFieldValue() },
                searching = searchState.searching,
                focused = searchState.focused
            )
        }
        items(state.topics, key = { it.id }) { topic ->
            val isExpanded = topic.id == expandedTopicId

            TopicItem(
                topic = topic,
                isExpanded = isExpanded,
                onUnitClick = onUnitClick,
                onTopicExpand = { expandedTopicId = if (isExpanded) -1 else topic.id }
            )
        }
    }
}

@Composable
fun TopicItem(
    topic: Topic,
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
    onUnitClick: (UnitBase) -> Unit = {},
    onTopicExpand: () -> Unit = {}
) {
    val expandIcon = with(Icons.Default) { if (isExpanded) KeyboardArrowUp else KeyboardArrowDown }


    Card(modifier = Modifier.fillMaxWidth() then modifier) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .clickable(onClick = onTopicExpand),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(topic.name, style = MaterialTheme.typography.titleMedium)
                IconButton(onClick = onTopicExpand) {
                    Icon(expandIcon, contentDescription = null)
                }
            }
            AnimatedContent(targetState = isExpanded, label = "") { expanded ->
                if (expanded) {
                    Column {
                        topic.units.forEach { unit ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onUnitClick(unit) }
                                    .padding(8.dp)
                            ) {
                                Text("${unit.position}. ${unit.name}")
                            }
                        }
                    }
                }
            }
        }
    }
}