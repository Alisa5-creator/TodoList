package com.example.todolist

import com.example.todolist.data.local.TodoDataSource
import com.example.todolist.data.model.TodoItemDto
import com.example.todolist.data.repository.TodoRepositoryImpl
import com.example.todolist.domain.usecase.GetTodosUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TodoUnitTest {

    private val testData = listOf(
        TodoItemDto(1, "Купить молоко", "2 литра, обезжиренное", false),
        TodoItemDto(2, "Позвонить маме", "Спросить про выходные", true),
        TodoItemDto(3, "Сделать ДЗ по Android", "Clean Architecture + Compose", false)
    )

    private fun createRepository() = TodoRepositoryImpl(TodoDataSource { testData })

    @Test
    fun getTodosUseCase_returnsThreeTodos() = runTest {
        val useCase = GetTodosUseCase(createRepository())

        val result = useCase()

        assertEquals(3, result.size)
    }

    @Test
    fun toggleTodo_changesIsCompleted() = runTest {
        val repository = createRepository()
        assertFalse(repository.getTodos().first { it.id == 1 }.isCompleted)

        repository.toggleTodo(1)
        assertTrue(repository.getTodos().first { it.id == 1 }.isCompleted)

        repository.toggleTodo(1)
        assertFalse(repository.getTodos().first { it.id == 1 }.isCompleted)
    }
}