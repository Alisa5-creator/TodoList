package com.example.todolist.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.TodoJsonDataSource
import com.example.todolist.data.repository.TodoRepositoryImpl
import com.example.todolist.domain.model.TodoItem
import com.example.todolist.domain.usecase.GetTodosUseCase
import com.example.todolist.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel(
    private val getTodos: GetTodosUseCase,
    private val toggleTodo: ToggleTodoUseCase
) : ViewModel() {

    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todos.asStateFlow()

    init {
        viewModelScope.launch { _todos.value = getTodos() }
    }

    fun onToggle(id: Int) {
        viewModelScope.launch {
            toggleTodo(id)
            _todos.value = getTodos()
        }
    }
}

class TodoViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = TodoRepositoryImpl(TodoJsonDataSource(context.applicationContext))
        return TodoViewModel(GetTodosUseCase(repository), ToggleTodoUseCase(repository)) as T
    }
}