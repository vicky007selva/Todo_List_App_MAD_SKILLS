package com.example.todo_list_app.data

import android.content.Context
import androidx.room.Database

import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoListInfo::class], version = 1, exportSchema = false)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun getTodoDao():TodoDao
    companion object
    {
        @Volatile
        private var INSTANCE:TodoDatabase? = null
        fun getDatabase(context: Context):TodoDatabase
        {
            return INSTANCE ?: synchronized(this)
            {
                Room.databaseBuilder(context,TodoDatabase::class.java,"TodoListDatabase").
                    build().
                    also { INSTANCE=it }
            }
        }
    }
}