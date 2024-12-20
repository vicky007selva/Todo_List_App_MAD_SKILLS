package com.example.todo_list_app.ui.theme.modify

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.todo_list_app.TodoListViewModel
import com.example.todo_list_app.ui.theme.home.TopBar
import com.example.todo_list_app.ui.theme.navigation.NavigationDestination

object ModifyActivityDestination: NavigationDestination
{
    override val route: String = "modifyActivity"
}

@Composable
fun ModifyScreen(modifier: Modifier = Modifier,
                 viewModel: TodoListViewModel,
                 navigateBack :()->Unit = {}
                 ) {

    val coroutineScope = rememberCoroutineScope()

    val selectedCard = viewModel.selectedCard
    var title by remember { mutableStateOf(selectedCard?.title ?: "") }
    var details by remember { mutableStateOf(selectedCard?.details ?: "") }

    Scaffold(modifier=modifier,
        topBar = {
            TopBar(
                modifier=modifier,
                isEditScreen = true,
                viewModel = viewModel,
                coroutineScope = coroutineScope,
                navigateBack = navigateBack,
                title = title,
                details = details,
            )
        }
        ) {
        innerPadding ->

        Column (modifier=modifier.padding(innerPadding).fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {




                Text(text="Edit Details")
                TextField(
                    value = title,
                    onValueChange = {
                        title = it
                    }
                )
                TextField(
                    value = details,
                    onValueChange = {

                        details = it
                    }
                )

        }
    }

}