package com.machinecode.kmp_github.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.machinecode.kmp_github.domain.model.RepositoryDetails
import com.machinecode.kmp_github.ui.viewmodel.GithubVM
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Preview
@Composable
fun HomeScreen(
    onRepoClick: (RepositoryDetails) -> Unit
) {
    val viewModel: GithubVM = koinViewModel()
//    val context = LocalContext.current

    val searchQuery by viewModel.searchQuery.collectAsState()
    val repositories by viewModel.repositories.collectAsState()
    val fetchStatus by viewModel.fetchStatus.collectAsState()
    val canFetchMessage by viewModel.messageFetch.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }.map { it.visibleItemsInfo.lastOrNull()?.index }
            .distinctUntilChanged().collect { lastVisibleIndex ->
                val totalCount = repositories.size
                if (lastVisibleIndex != null && lastVisibleIndex >= totalCount - 1) {
                    viewModel.fetchRepositories(viewModel.searchQuery.value)
                }
            }
    }

    LaunchedEffect(fetchStatus) {
        if (fetchStatus == false) {
//            Toast.makeText(context, "No internet. Showing local data.", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(canFetchMessage) {
        canFetchMessage?.let {
//            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearCanFetchMessage()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = viewModel::onSearchQueryChange,
                modifier = Modifier.testTag("ttSearchInput").weight(1f).height(56.dp),
                placeholder = { Text("Search") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                modifier = Modifier.testTag("ttBtnSearch"), onClick = viewModel::onSearchClick
            ) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search",
                    tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            }

            IconButton(
                modifier = Modifier.testTag("ttBtnSort"), onClick = viewModel::toggleSortByStars
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Sort,
                    contentDescription = "Sort",
                    tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            }
        }

        if (repositories.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No repositories found",
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.testTag("ttEmptyStateText")
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.testTag("ttLcRepository"),
                state = listState,
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(repositories) { repo ->
                    RepositoryItemCard(repo, onClick = { onRepoClick(repo) })
                }
            }
        }
    }
}

@Composable
fun RepositoryItemCard(
    repo: RepositoryDetails, onClick: () -> Unit
) {
    val textColor = MaterialTheme.colorScheme.onSurface

    Card(
        modifier = Modifier.testTag("ttRepositoryItem").fillMaxWidth().clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            AsyncImage(
                model = repo.owner.avatarUrl,
                contentDescription = null,
                modifier = Modifier.size(48.dp).clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = repo.owner.login.toString(),
                    style = MaterialTheme.typography.titleSmall,
                    color = textColor
                )
                Text(
                    text = repo.name.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = textColor
                )
                Text(
                    text = repo.description.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = textColor
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if (repo.stargazersCountStr.isNotEmpty()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.StarOutline,
                                contentDescription = "Stars",
                                tint = if (isSystemInDarkTheme()) Color(0xFFFFD700) else Color(
                                    0xFFDAA520
                                ),
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = repo.stargazersCountStr.toString(), color = textColor
                            )
                        }
                    }

                    if (repo.languageColor != null) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier.size(10.dp).clip(CircleShape)
                                    .background(repo.languageColor)
                            )
                            Text(
                                text = repo.language, color = textColor
                            )
                        }
                    }
                }
            }
        }
    }
}
