package com.camp.camp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.camp.camp.ui.theme.PinkIoasys

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen() {

    val list = remember { mutableStateListOf<String>() }
    val checkedStateList = remember { mutableStateListOf<Boolean>() }
    var newItemText by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Box(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .fillMaxSize()
                    .background(PinkIoasys)
                    .height(63.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Lista de Tarefas",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }

        item {
            Row(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                TextField(
                    singleLine = true,
                    value = newItemText,
                    onValueChange = { newItemText = it },
                    modifier = Modifier.padding(end = 8.dp, bottom = 41.dp),
                    textStyle = LocalTextStyle.current.copy(color = Color.Black),
                    label = {
                        Text(
                            modifier = Modifier,
                            fontSize = 12.sp,
                            text = "Nova Tarefa"
                        )
                    },
                    shape = RoundedCornerShape(0.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        unfocusedLabelColor = PinkIoasys,
                        focusedLabelColor = PinkIoasys,
                        cursorColor = PinkIoasys,
                    )
                )

                Button(
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(containerColor = PinkIoasys),
                    onClick = {
                        if (newItemText.isNotBlank()) {
                            list.add(newItemText)
                            checkedStateList.add(false)
                            newItemText = ""
                        }
                    }
                ) {
                    Text(
                        text ="ADD",
                        fontSize = 12.sp,
                        color = Color.White)
                }
            }
        }

        items(
            items = list,
            key = { it }
        ) { message ->
            DismissContainer(
                onDelete = {
                    val index = list.indexOf(message)
                    list.removeAt(index)
                    checkedStateList.removeAt(index)
                },
            ) {
                val index = list.indexOf(message)
                val isChecked = checkedStateList[index]

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                ) {
                    Icon(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        imageVector = if (isChecked) Icons.Filled.CheckCircle else Icons.Filled.Info,
                        contentDescription = "Icone de verificação",
                        tint = PinkIoasys
                    )

                    Text(
                        modifier = Modifier.weight(1f),
                        text = message,
                        color = Color.Black,
                    )

                    Checkbox(
                        modifier = Modifier.padding(horizontal = 46.dp),
                        checked = isChecked,
                        onCheckedChange = { checkedStateList[index] = it },
                        enabled = true,
                        colors = CheckboxDefaults.colors(PinkIoasys),
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DismissContainer(
    onDelete: () -> Unit,
    content: @Composable () -> Unit,
) {
    val state = rememberDismissState(

        confirmValueChange = { value ->
            if (value == DismissValue.DismissedToEnd) {
                onDelete()
                true
            } else {
                false
            }
        })
    SwipeToDismiss(
        modifier = Modifier,
        state = state,
        background = { BackgroundDelete() },
        dismissContent = { content() },
        directions = setOf(DismissDirection.StartToEnd)
    )
}

@Composable
private fun BackgroundDelete() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        contentAlignment = Alignment.CenterStart
    ) {
        Icon(
            modifier = Modifier.padding(start = 26.dp),
            imageVector = Icons.Default.Delete,
            contentDescription = "Icone de Lixeira",
            tint = Color.White
        )
    }
}


@Preview
@Composable
fun SecondScreenPreview() {
    SecondScreen()
}



