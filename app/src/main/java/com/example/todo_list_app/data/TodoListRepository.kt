package com.example.todo_list_app.data

import kotlinx.coroutines.flow.Flow

class TodoListRepository(private val todoDao: TodoDao) {

    suspend fun insert(todoListInfo: TodoListInfo)
    {
        todoDao.insert(todoListInfo)
    }

    suspend fun update(todoListInfo: TodoListInfo)
    {
        todoDao.update(todoListInfo)
    }

    suspend fun delete(todoListInfo: TodoListInfo)
    {
        todoDao.delete(todoListInfo)
    }

    fun getListStream(): Flow<List<TodoListInfo>> = todoDao.getList()


}