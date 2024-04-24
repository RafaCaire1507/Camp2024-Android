package com.camp.camp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import com.camp.camp.ui.theme.PinkIoasys

@Composable
fun SecondScreen() {

    var item by remember {
        mutableStateOf("")
    }

    Row (modifier = Modifier
        .fillMaxSize()
        .background(color = PinkIoasys),
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(value = item, onValueChange = { value ->

            item = value
        }, label = {
            Text(text = "Nova tarefa")
        } )

        Button(onClick = {  }) {

            Text(text = "ADD")

        }


    }


}

@Preview
@Composable
fun SecondScreenPreview() {
    SecondScreen()
}



