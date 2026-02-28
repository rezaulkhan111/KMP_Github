package com.machinecode.kmp_github.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeviceHub
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.machinecode.kmp_github.domain.model.RepositoryDetails
import com.machinecode.kmp_github.ui.viewmodel.GithubVM
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailsScreen(repoId: String?) {
    val viewModel: GithubVM = koinViewModel()

    LaunchedEffect(repoId) {
        viewModel.getRepositoryById(
            if (!repoId.isNullOrEmpty()) {
                repoId.toInt()
            } else {
                0
            }
        )
    }

    val repo by viewModel.repositoryDetails.collectAsState()

    if (repo != null) {
        DetailsScreenContent(repo!!)
    } else {
        Text("Repository not found", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun DetailsScreenContent(repo: RepositoryDetails) {
    Column(
        modifier = Modifier
            .testTag("ttDetailsScreen")
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = repo.owner.avatarUrl,
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(140.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.DarkGray),
                contentScale = ContentScale.Crop
            )

            Text(
                text = repo.owner.login.toString(),
                style = MaterialTheme.typography.titleSmall,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 16.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = repo.name.toString(),
                style = MaterialTheme.typography.titleMedium,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = repo.description.toString(),
                style = MaterialTheme.typography.bodySmall,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (repo.stargazersCountStr.isNotEmpty()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.StarOutline,
                        contentDescription = "Stars",
                        tint = if (isSystemInDarkTheme()) Color(0xFFFFD700) else Color(0xFFDAA520),
                        modifier = Modifier.size(20.dp)
                    )

                    Text(
                        text = "${repo.stargazersCountStr} stars",
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = 6.dp)
                    )
                }
            }

            if (repo.forksCount > 0) {
                Spacer(modifier = Modifier.width(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.DeviceHub,
                        contentDescription = "Forks",
                        tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        modifier = Modifier.size(20.dp)
                    )

                    Text(
                        text = "${repo.forksCount} forks",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(start = 6.dp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            if (repo.languageColor != null) {
                Spacer(modifier = Modifier.width(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(15.dp)
                            .clip(CircleShape)
                            .background(repo.languageColor)
                    )
                    Text(
                        text = repo.language,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(start = 6.dp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Current branch",
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = repo.defaultBranch,
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = repo.updatedAt,
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 10.dp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}