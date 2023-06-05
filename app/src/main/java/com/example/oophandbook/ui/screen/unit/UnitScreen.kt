package com.example.oophandbook.ui.screen.unit

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.oophandbook.R
import com.example.oophandbook.domain.model.Content
import com.example.oophandbook.domain.model.UnitBase
import com.example.oophandbook.ui.components.LoadingIndicator

@Composable
fun UnitScreen(
    viewModel: UnitViewModel = hiltViewModel(),
    onNavigateToUnit: (Int) -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    when (state) {
        UnitUiState.Loading -> LoadingIndicator()
        UnitUiState.UnitError -> {}
        is UnitUiState.Content -> UnitScreen(
            state = state as UnitUiState.Content,
            onBookmarkToggle = viewModel::toggleBookmark,
            onNavigateToUnit = { unit -> onNavigateToUnit(unit.id) },
            onCopyCode = {
                clipboardManager.setText(it)
                Toast.makeText(context, R.string.copied, Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Composable
fun UnitScreen(
    state: UnitUiState.Content,
    onBookmarkToggle: () -> Unit = {},
    onNavigateToUnit: (UnitBase) -> Unit = {},
    onCopyCode: (AnnotatedString) -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
        ) {
            item {
                Text(state.unit.name, style = MaterialTheme.typography.headlineMedium)
            }
            items(state.unit.content) { content ->
                when (content) {
                    is Content.Text -> TextContent(content)
                    is Content.Code -> CodeContent(
                        content = content,
                        onCopyContent = onCopyCode
                        //modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
        UnitFooter(
            isBookmarked = state.isBookmarked,
            next = state.next,
            previous = state.previous,
            modifier = Modifier.fillMaxWidth(),
            onBookmarkToggle = onBookmarkToggle,
            onNavigateToNext = { onNavigateToUnit(state.next!!) },
            onNavigateToPrevious = { onNavigateToUnit(state.previous!!) },
        )
    }
}

@Composable
fun CodeContent(
    content: Content.Code,
    modifier: Modifier = Modifier,
    onCopyContent: (AnnotatedString) -> Unit = {}
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = { onCopyContent(AnnotatedString(content.code)) }) {
                Text(stringResource(R.string.copy))
            }
        }
        Text(
            text = content.code,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun TextContent(content: Content.Text) {
    Text(
        text = content.text,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun UnitFooter(
    modifier: Modifier = Modifier,
    isBookmarked: Boolean = false,
    next: UnitBase? = null,
    previous: UnitBase? = null,
    onBookmarkToggle: () -> Unit = {},
    onNavigateToNext: () -> Unit = {},
    onNavigateToPrevious: () -> Unit = {}
) {
    val bookmarkIcon = with(Icons.Default) { if (isBookmarked) Favorite else FavoriteBorder }

    Row(modifier = modifier, horizontalArrangement = Arrangement.Center) {
        if (previous != null) {
            UnitNavButton(
                unit = previous,
                icon = Icons.Default.KeyboardArrowLeft,
                modifier = Modifier.weight(1f),
                onClick = onNavigateToPrevious
            )
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
        IconButton(onClick = onBookmarkToggle) {
            Icon(imageVector = bookmarkIcon, contentDescription = null)
        }
        if (next != null) {
            UnitNavButton(
                unit = next,
                icon = Icons.Default.KeyboardArrowRight,
                modifier = Modifier.weight(1f),
                onClick = onNavigateToNext
            )
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun UnitNavButton(
    unit: UnitBase,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(modifier = modifier, onClick = onClick) {
        Text(
            text = unit.name,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Icon(imageVector = icon, contentDescription = null)
    }
}