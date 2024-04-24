package com.camp.camp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.camp.camp.ui.theme.Camp2024Theme
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://keyclock.cluster-dev.ioasys.com.br/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        val service = retrofit.create(LoginService::class.java)
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Camp2024Theme {

                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LoginScreen(onLoginClick = { email, password ->
                            val call = service.login(
                                "password",
                                "camp-ioasys-2024",
                                "BiKzHxr9ZoZRDlLjx6qG7QfnDhIoQdIf",
                                email,
                                password,
                                "email"
                            )
                            call.enqueue(object : Callback<LoginResponse> {
                                override fun onResponse(
                                    p0: Call<LoginResponse>,
                                    p1: Response<LoginResponse>
                                ) {
                                    if (p1.isSuccessful) {
                                        navController.navigate("tasklist")
                                    } else {
                                        Toast.makeText(this@MainActivity, "Usuario invalido", Toast.LENGTH_SHORT).show()
                                    }
                                }
                                override fun onFailure(p0: Call<LoginResponse>, p1: Throwable) {

                                }
                            })
                        })
                    }

                    composable("tasklist") {
                        SecondScreen()
                    }
                }
            }
        }
    }
}