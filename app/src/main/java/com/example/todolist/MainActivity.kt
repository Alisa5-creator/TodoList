package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.todolist.navigation.TodoNavGraph
import com.example.todolist.presentation.viewmodel.TodoViewModel
import com.example.todolist.presentation.viewmodel.TodoViewModelFactory
import com.example.todolist.ui.theme.TodoListTheme

class MainActivity : ComponentActivity() {

    private val viewModel: TodoViewModel by viewModels {
        TodoViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TodoNavGraph(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}