package com.example.todo_list_app.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todo_list_app.TodoListViewModel

import com.example.todo_list_app.ui.theme.home.TodoListDestionation
import com.example.todo_list_app.ui.theme.home.TodoListScreen
import com.example.todo_list_app.ui.theme.modify.ModifyActivityDestination
import com.example.todo_list_app.ui.theme.modify.ModifyScreen


@Composable
fun TodoListNavGraph(modifier: Modifier = Modifier,
                     navController: NavHostController,
                     onClickAddButton:()->Unit,
                     viewModel:TodoListViewModel,
                     onAddInfo:()->Unit,
                     onCancelInfo:()->Unit,
                     isOpenAdd : Boolean = false,

) {

    NavHost(
        navController = navController,
        startDestination = TodoListDestionation.route ,
        modifier = modifier

    ) {
        composable(route = TodoListDestionation.route) {
            TodoListScreen(modifier = modifier,
                viewModel = viewModel,
                onClickAddButton = onClickAddButton,
                onAddInfo = onAddInfo,
                onCancelInfo = onCancelInfo,
                onValueChange = viewModel::updateUiState,
                isAddButton = isOpenAdd,
                navigateToedit = { navController.navigate(ModifyActivityDestination.route) }
            )
        }
        composable(route = ModifyActivityDestination.route
            ) {
            ModifyScreen(
                modifier=modifier,
                viewModel = viewModel,
                navigateBack = {
                    navController.popBackStack()
                }
                )
        }
    }




}
