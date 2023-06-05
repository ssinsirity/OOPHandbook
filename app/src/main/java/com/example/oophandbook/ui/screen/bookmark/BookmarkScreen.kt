package com.example.oophandbook.ui.screen.bookmark

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.oophandbook.R
import com.example.oophandbook.domain.model.Topic
import com.example.oophandbook.domain.model.UnitBase
import com.example.oophandbook.ui.components.LoadingIndicator

@Composable
fun BookmarkScreen(
    viewModel: BookmarkViewModel = hiltViewModel(),
    onNavigateToUnit: (Int) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        BookmarkUiState.Loading -> LoadingIndicator()
        BookmarkUiState.NoBookmarks -> NoBookmarksScreen()
        is BookmarkUiState.Content -> BookmarkScreen(
            state = uiState as BookmarkUiState.Content,
            onOpenUnit = { onNavigateToUnit(it.id) },
            onRemoveUnit = viewModel::removeUnit
        )
    }
}

@Composable
fun NoBookmarksScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.no_bookmars))
    }
}

@Composable
fun BookmarkScreen(
    state: BookmarkUiState.Content,
    onOpenUnit: (UnitBase) -> Unit = {},
    onRemoveUnit: (UnitBase) -> Unit = {}
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        items(state.bookmarks) { (topic, units) ->
            BookmarkedTopicItem(
                topic = topic,
                units = units,
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                onOpenUnit = onOpenUnit,
                onRemoveUnit = onRemoveUnit
            )
        }
    }
}

@Composable
fun BookmarkedTopicItem(
    topic: Topic,
    units: List<UnitBase>,
    modifier: Modifier = Modifier,
    onOpenUnit: (UnitBase) -> Unit = {},
    onRemoveUnit: (UnitBase) -> Unit = {}
) {
    Card(modifier = Modifier.fillMaxWidth() then modifier) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(topic.name, style = MaterialTheme.typography.titleMedium)

            units.forEach { unit ->
                BookmarkedUnitItem(
                    unit = unit,
                    modifier = Modifier.fillMaxSize(),
                    onUnitClick = { onOpenUnit(unit) },
                    onRemoveClick = { onRemoveUnit(unit) })
            }
        }
    }
}

@Composable
fun BookmarkedUnitItem(
    unit: UnitBase,
    modifier: Modifier = Modifier,
    onUnitClick: () -> Unit = {},
    onRemoveClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .clickable(onClick = onUnitClick)
    ) {
        Text("${unit.position}. ${unit.name}", modifier = Modifier.weight(1f))
        IconButton(onClick = onRemoveClick) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        }
    }
}