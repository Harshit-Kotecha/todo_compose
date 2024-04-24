package com.example.firstkt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val tasks: MutableList<String> = remember {
                mutableStateListOf()
            }
            App(tasks)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Previewer() {
    val tasks: MutableList<String> = remember {
        mutableStateListOf()
    }
    tasks.add("A")
    tasks.add("B")
    tasks.add("C")
    App(tasks)
}

@Composable
fun App(tasks: MutableList<String>) {
    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxHeight()) {
        Column {
            Header()
            TasksList(tasks)
        }
        AddNewTasks(tasks)
    }
}

@Composable
fun Header() {
    Text(
        text = "Todo App",
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Red)
            .padding(all = 16.dp)
    )
}

@Composable
fun Tile(data: String) {

    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text(text = data, modifier = Modifier.padding(8.dp))
        Icon(
            Icons.Rounded.Delete,
            contentDescription = stringResource(id = R.string.app_name)
        )
    }
}


@Composable
fun TasksList(items: MutableList<String>) {
    Log.d("TASKS", items.toString())

    LazyColumn {
        items(items.size) { i ->
            Tile(data = items[i])
        }
    }
}

@Composable
fun AddNewTasks(items: MutableList<String>) {
    val task = remember {
        mutableStateOf("")
    }
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        AddTask(task)
        AddBtn(task, items)
    }
}

@Composable
fun AddTask(task: MutableState<String>) {
    TextField(
        value = task.value,
        onValueChange = { value ->
            task.value = value
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        )
    )
}

@Composable
fun AddBtn(task: MutableState<String>, items: MutableList<String>) {
    Button(
        onClick = {
            Log.d("ADD", "tasks added")
            items.add(task.value)
            task.value = ""
        }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
    ) {
        Text(text = "Add Task")
    }
}