package com.example.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolist.presentation.ui.screen.TodoDetailScreen
import com.example.todolist.presentation.ui.screen.TodoListScreen
import com.example.todolist.presentation.viewmodel.TodoViewModel

@Composable
fun TodoNavGraph(viewModel: TodoViewModel, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list",
        modifier = modifier
    ) {
        composable("list") {
            TodoListScreen(
                viewModel = viewModel,
                onTodoClick = { id -> navController.navigate("detail/$id") }
            )
        }
        composable(
            route = "detail/{todoId}",
            arguments = listOf(navArgument("todoId") { type = NavType.IntType })
        ) { entry ->
            val id = entry.arguments?.getInt("todoId") ?: return@composable
            TodoDetailScreen(
                todoId = id,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}