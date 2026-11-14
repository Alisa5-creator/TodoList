package com.example.todolist.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.presentation.ui.component.TodoItemCard
import com.example.todolist.presentation.viewmodel.TodoViewModel

@Composable
fun TodoListScreen(
    viewModel: TodoViewModel,
    onTodoClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val todos by viewModel.todos.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(todos, key = { it.id }) { item ->
            TodoItemCard(
                item = item,
                onClick = { onTodoClick(item.id) },
                onToggle = { viewModel.onToggle(item.id) }
            )
        }
    }
}