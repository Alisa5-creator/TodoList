package com.example.todolist.data.local

import android.content.Context
import com.example.todolist.data.model.TodoItemDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun interface TodoDataSource {
    fun getTodos(): List<TodoItemDto>
}

class TodoJsonDataSource(private val context: Context) : TodoDataSource {

    private val gson = Gson()

    override fun getTodos(): List<TodoItemDto> {
        val json = context.assets.open("todos.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<TodoItemDto>>() {}.type
        return gson.fromJson(json, type)
    }
}