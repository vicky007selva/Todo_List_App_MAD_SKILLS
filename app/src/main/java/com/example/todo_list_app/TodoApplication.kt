package com.example.todo_list_app

import android.app.Application
import com.example.todo_list_app.data.AppContainer

class TodoApplication:Application() {
   lateinit var appContainer: AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}