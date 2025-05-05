package com.example.homefinance.Core.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.homefinance.Screen.LoginScreen
import com.example.homefinance.Screen.MainScreen
import com.example.homefinance.Screen.UserScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable <Login> {
            LoginScreen {
                navController.navigate(Main)
            }
        }

        composable<Main> {
            MainScreen { navController.navigate(Main)}
        }
    }
}