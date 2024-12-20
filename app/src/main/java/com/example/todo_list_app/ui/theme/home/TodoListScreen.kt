package com.example.todo_list_app.ui.theme.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo_list_app.AppViewModelProvider
import com.example.todo_list_app.ItemDetails
import com.example.todo_list_app.TodoListViewModel
import com.example.todo_list_app.data.TodoListInfo
import com.example.todo_list_app.ui.theme.add.AddActivity
import com.example.todo_list_app.ui.theme.navigation.NavigationDestination


object TodoListDestionation:NavigationDestination{
    override val route = "todoList"

}

@SuppressLint("SuspiciousIndentation", "UnrememberedMutableState")
@Composable
fun TodoListScreen(modifier: Modifier = Modifier,
                   viewModel: TodoListViewModel = viewModel(factory = AppViewModelProvider.factory),
                   onClickAddButton: ()->Unit,
                   onAddInfo:()->Unit,
                   onCancelInfo: ()->Unit,
                   onValueChange:(ItemDetails)->Unit,
                   isAddButton: Boolean = false,
                   navigateToedit:()->Unit,
) {

    val todoListUistate by viewModel.todoList.collectAsState()



    Scaffold(
        topBar = { TopBar(modifier) },
        floatingActionButton = {
            if(!isAddButton) {
                AddButton(
                    modifier = modifier,
                    onClickAddButton = onClickAddButton
                )
            }
        },
        modifier = modifier,

        ) { innerPadding ->

        if(!isAddButton)
        {

            LazyColumn(modifier = modifier.padding(innerPadding)) {
                items(items =todoListUistate.todoList )
                { item ->

                    DetailCard(item=item,
                        onEditButton = {
                            viewModel.selectedCard = item
                            navigateToedit()
                        }
                        )
                    Spacer(modifier = modifier.height(10.dp))

                }
            }
        }
        else {
            AddActivity(modifier=modifier.padding(innerPadding),
                onAddInfo=onAddInfo,
                onCancelInfo=onCancelInfo,
                onValueChange = onValueChange,
                itemDetails = viewModel.todoItemUistate.itemDetails

            )
        }

    }
}


@Composable
fun AddButton(modifier: Modifier = Modifier, onClickAddButton: () -> Unit) {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.primary,
        onClick = onClickAddButton,
        modifier = modifier
    )  {
        Icon(
            Icons.Filled.Add,
            contentDescription = "Add activity to todo list",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier=modifier
        )
    }
}


@Composable
fun DetailCard(modifier: Modifier = Modifier,
               item: TodoListInfo,
               onEditButton: ()->Unit
) {
    Card(
        onClick = onEditButton,
        shape = CardDefaults.elevatedShape,
        modifier=modifier.fillMaxWidth().size(100.dp).padding(8.dp)

    ) {
        Column (modifier=modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(text = item.title, fontWeight = FontWeight.ExtraBold)
            Text(text = item.details)

        }

    }
}