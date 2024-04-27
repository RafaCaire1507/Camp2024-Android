package com.camp.camp

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.camp.camp.ui.theme.PinkIoasys

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen() {

    val list = remember { mutableStateListOf("item1") }
    var newItemText by remember { mutableStateOf("") }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {


        item {
            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp)
            ) {
                TextField(
                    singleLine = true,
                    value = newItemText,
                    onValueChange = { newItemText = it },
                    modifier = Modifier
                        .padding(end = 8.dp),
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
                        cursorColor = PinkIoasys
                    )
                )


                Button(modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PinkIoasys
                    ),
                    onClick = {
                        if (newItemText.isNotBlank()) {
                            list.add(newItemText)
                            newItemText = ""
                        }
                    }
                ) {
                    Text("ADD")
                }
            }
        }
        items(
            items = list,
            key = { it }

        ) { message ->
            DismissContainer(
                onDelete = {
                    list -= message

                },
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                        .height(54.dp)
                        .background(Color.White),
                    text = message,
                    color = Color.Black,
                )
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
        }
    )
    SwipeToDismiss(
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
            .padding(bottom = 16.dp)
            .fillMaxSize()
            .background(Color.Red),
        contentAlignment = Alignment.CenterStart
    ) {
        Icon(
            modifier = Modifier
                .padding(start = 26.dp),
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



