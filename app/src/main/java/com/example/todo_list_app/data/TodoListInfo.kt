package com.example.todo_list_app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todolist")
data class TodoListInfo(
    @PrimaryKey(autoGenerate = true) val id:Int=0,
    val title:String,
    val details:String,
    val date:String
)
