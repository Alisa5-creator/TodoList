package com.example.todolist.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todolist.presentation.viewmodel.TodoViewModel

@Composable
fun TodoDetailScreen(
    todoId: Int,
    viewModel: TodoViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val todos by viewModel.todos.collectAsState()
    val item = todos.find { it.id == todoId }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Назад"
            )
        }
        if (item != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = item.title, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.description, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (item.isCompleted) "Выполнено" else "Не выполнено",
                style = MaterialTheme.typography.bodyLarge,
                color = if (item.isCompleted) Color(0xFF2E7D32) else Color.Red
            )
        }
    }
}