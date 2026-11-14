package com.example.todolist.data.repository

import com.example.todolist.data.local.TodoDataSource
import com.example.todolist.data.model.toDomain
import com.example.todolist.domain.model.TodoItem
import com.example.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepositoryImpl(private val dataSource: TodoDataSource) : TodoRepository {

    private var cache: List<TodoItem>? = null

    override suspend fun getTodos(): List<TodoItem> {
        return cache ?: withContext(Dispatchers.IO) {
            dataSource.getTodos().map { it.toDomain() }
        }.also { cache = it }
    }

    override suspend fun toggleTodo(id: Int) {
        cache = getTodos().map { item ->
            if (item.id == id) item.copy(isCompleted = !item.isCompleted) else item
        }
    }
}