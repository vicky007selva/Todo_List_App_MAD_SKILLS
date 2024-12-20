package com.example.todo_list_app.data

import android.content.Context

class AppContainer(private val context: Context) {
    val todoListRepository:TodoListRepository by lazy {
        TodoListRepository(TodoDatabase.getDatabase(context).getTodoDao())
    }
}