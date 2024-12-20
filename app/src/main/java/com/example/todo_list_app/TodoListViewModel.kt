package com.example.todo_list_app

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_list_app.data.TodoListInfo
import com.example.todo_list_app.data.TodoListRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TodoListViewModel(private val todoListRepository: TodoListRepository):ViewModel() {

    companion object{
        private const val TIME_OUT_MILLIS = 5000L
        private const val TAG = "TodoListViewModel"
    }

    var selectedCard:TodoListInfo?=null

    var todoModUistate by mutableStateOf(TodoModUIState())
    private set


    var todoItemUistate by mutableStateOf(TodoItemUIState())
    private set

    val todoList: StateFlow<TodoListUistate> = todoListRepository.getListStream().map {
        TodoListUistate(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIME_OUT_MILLIS),
        initialValue = TodoListUistate()
    )
    
    fun updateUiState(itemDetails: ItemDetails)
    {
        todoItemUistate = TodoItemUIState(itemDetails = itemDetails,validateInput(itemDetails))
    }

    fun updateUiStateForModifyOrDelete(todoListInfo:TodoListInfo)
    {
        todoModUistate = TodoModUIState(todoListInfo=todoListInfo,validateModInput(todoListInfo))
    }


    suspend fun insertInfo()
    {
        if(validateInput()) {
            todoListRepository.insert(todoItemUistate.itemDetails.toTodoListInfo())
        }
        todoItemUistate = TodoItemUIState()
    }

    fun cancelInfo()
    {
        todoItemUistate = TodoItemUIState()
    }

    suspend fun updateInfo()
    {
        Log.d(TAG,"deleteInfo")
        if(validateModInput())
        {
            todoListRepository.update(todoModUistate.todoListInfo)
        }
        todoModUistate = TodoModUIState()
    }

    suspend fun deleteInfo()
    {
        Log.d(TAG,"deleteInfo")
        if(validateModInput())
        {
            todoListRepository.delete(todoModUistate.todoListInfo)
        }
        todoModUistate = TodoModUIState()
    }

    private fun validateModInput(uistate: TodoListInfo=todoModUistate.todoListInfo): Boolean {
        return with(uistate)
        {
            title.isNotBlank() && details.isNotBlank()
        }
    }
    
    private fun validateInput(uistate: ItemDetails=todoItemUistate.itemDetails): Boolean {
        return with(uistate)
        {
            title.isNotBlank() && details.isNotBlank() 
        }
    }

}

data class ItemDetails(
    val id:Int = 0,
    val title:String="",
    val details:String="",
    val date:String=""
)

fun TodoListInfo.toItemDetails():ItemDetails =
    ItemDetails(id=id,title=title, date = date)

fun ItemDetails.toTodoListInfo():TodoListInfo = 
    TodoListInfo(id=id,title=title,details=details,date=date)
data class TodoItemUIState(val itemDetails:ItemDetails = ItemDetails(),val isEntryValid:Boolean = false)
data class TodoModUIState(val todoListInfo: TodoListInfo=TodoListInfo(date = "", details = "", title = ""), val isEntryValid: Boolean=false)

data class TodoListUistate(val todoList:List<TodoListInfo> = listOf())