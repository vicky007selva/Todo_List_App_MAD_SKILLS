package com.example.todo_list_app.ui.theme.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.todo_list_app.ItemDetails
import com.example.todo_list_app.ui.theme.navigation.NavigationDestination



@Composable
fun AddActivity(itemDetails: ItemDetails,
                modifier: Modifier = Modifier,
                onAddInfo: () -> Unit,
                onCancelInfo:()->Unit,
                onValueChange: (ItemDetails) -> Unit={})
{
    Column(modifier=modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = itemDetails.title,
            onValueChange = {onValueChange(itemDetails.copy(title=it))}
        )
        TextField(
            value = itemDetails.details,
            onValueChange = {onValueChange(itemDetails.copy(details = it))}
        )
        TextField(
            value = itemDetails.date,
            onValueChange = {
                onValueChange(itemDetails.copy(date = it))
            }
        )
        Button(onClick = onAddInfo) {
            Text(text = "Add")
        }
        Button(onClick = onCancelInfo)
        {
            Text(text = "Cancel")
        }
    }
}