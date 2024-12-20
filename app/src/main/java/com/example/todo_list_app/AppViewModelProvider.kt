package com.example.todo_list_app

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

object AppViewModelProvider {
    val factory = viewModelFactory {
        initializer {
            TodoListViewModel(getApplication().appContainer.todoListRepository)
        }
    }
}

fun CreationExtras.getApplication() = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TodoApplication)