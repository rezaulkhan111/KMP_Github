package com.machinecode.kmp_github.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.machinecode.kmp_github.network.GithubVM
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

//@Preview
@Composable
fun GithubScreen() {
    val viewModel = GithubVM()

    val repos by viewModel.repository.collectAsState()
    val scope = rememberCoroutineScope()

    var username by remember { mutableStateOf("JetBrains") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {

        // 🔍 Username TextField
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("GitHub project") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        // 🔄 Load Button
        Button(
            onClick = {
                scope.launch { viewModel.fetchRepository(username) }
            }, modifier = Modifier.align(Alignment.End)
        ) {
            Text("Load Repos")
        }

        Spacer(Modifier.height(20.dp))

        // 📦 Repo List
        if (repos.isNullOrEmpty()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text("No repositories loaded yet.", modifier = Modifier.align(Alignment.Center))
            }
        } else {
            LazyColumn {
                items(repos!!) { repo ->
                    RepoItem(repo.name.toString(), repo.description ?: "No description")
                }
            }
        }
    }
}

@Composable
fun RepoItem(name: String, desc: String) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        Text(text = name, style = MaterialTheme.typography.titleMedium)
        Text(text = desc, style = MaterialTheme.typography.bodyMedium)
    }
}
