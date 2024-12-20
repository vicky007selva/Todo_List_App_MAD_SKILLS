package com.example.todo_list_app

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todo_list_app.ui.theme.Todo_List_AppTheme
import com.example.todo_list_app.ui.theme.home.TodoListScreen
import com.example.todo_list_app.ui.theme.navigation.TodoListNavGraph
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Todo_List_AppTheme {
                val coroutineScope = rememberCoroutineScope()
                Scaffold(modifier = Modifier.fillMaxSize(),
                    ) { innerPadding ->
                    val viewModel:TodoListViewModel = viewModel(factory = AppViewModelProvider.factory)
                    var isOpenAdd by rememberSaveable {
                        mutableStateOf(false)
                    }

                    val  navController:NavHostController = rememberNavController()

                    TodoListNavGraph(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        onClickAddButton = {isOpenAdd=!isOpenAdd},
                        viewModel = viewModel,
                        onAddInfo = {coroutineScope.launch {
                                isOpenAdd = !isOpenAdd
                                viewModel.insertInfo()
                            }},
                        onCancelInfo ={coroutineScope.launch {
                            isOpenAdd = !isOpenAdd
                            viewModel.cancelInfo()
                        }},
                        isOpenAdd = isOpenAdd
                    )

                }
            }
        }
    }
}















@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    Todo_List_AppTheme {
//        TodoListScreen(onClickAddButton = {}, isAddButton = true, onAddInfo = {},
//            modifier = Modifier,
//            viewModel = viewModel(factory = AppViewModelProvider.factory),
//            onValueChange = {}
//        )
    }
}


