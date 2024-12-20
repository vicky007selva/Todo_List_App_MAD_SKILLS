package com.example.todo_list_app.ui.theme.home


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo_list_app.AppViewModelProvider
import com.example.todo_list_app.TodoListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier,
           isEditScreen: Boolean= false,
           coroutineScope: CoroutineScope = rememberCoroutineScope(),
           navigateBack:()->Unit ={},
           title:String ="",
           details:String ="",
           viewModel: TodoListViewModel = viewModel(factory = AppViewModelProvider.factory)
   ) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "TODO LIST APP",
                fontFamily = FontFamily.Serif
            )
        },
        navigationIcon = {
            if(isEditScreen) {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },
        actions = {
            if(isEditScreen) {
                IconButton(onClick = {
                    coroutineScope.launch {
                        viewModel.selectedCard?.let {
                            viewModel.updateUiStateForModifyOrDelete(it)
                            viewModel.deleteInfo()
                        }
                        navigateBack()
                    }

                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                IconButton(onClick = {
                    coroutineScope.launch {
                        viewModel.selectedCard?.let {
                            viewModel.updateUiStateForModifyOrDelete(viewModel.selectedCard!!.copy(details = details, title = title))
                            viewModel.updateInfo()
                        }
                        navigateBack()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Done",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),

        modifier = modifier
    )

}


