package com.example.todo_list_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoListInfo: TodoListInfo)

    @Query("SELECT * FROM todolist")
    fun getList(): Flow<List<TodoListInfo>>

    @Update
    suspend fun update(todoListInfo: TodoListInfo)

    @Delete
    suspend fun delete(todoListInfo: TodoListInfo)




}