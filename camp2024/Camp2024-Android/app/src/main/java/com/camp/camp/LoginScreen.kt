package com.camp.camp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.camp.camp.ui.theme.PinkIoasys


@Composable
fun LoginScreen(onLoginClick: (String, String) -> Unit) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PinkIoasys),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Seja bem vind@!", color = Color.White, fontSize = 30.sp)
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "Lista de tarefas",
            color = Color.White,
            fontSize = 24.sp
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, bottom = 16.dp, end = 16.dp, start = 16.dp),
            value = email,
            onValueChange = { value ->
                email = value
            }, label = {
                Text(text = "usuário")
            })
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, start = 16.dp),
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {
                password = it
            }, label = {
                Text(text = "senha")
            })

        Button(modifier = Modifier.padding(16.dp).fillMaxWidth(),onClick = { onLoginClick.invoke(email, password) }, colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color.Black,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.Gray,
        ), enabled = email.isNotEmpty() && password.isNotEmpty()) {
            Text(text = "Continuar")
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginClick = {email, password->

    })
}