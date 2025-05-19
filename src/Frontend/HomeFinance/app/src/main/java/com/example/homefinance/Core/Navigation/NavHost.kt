package com.example.homefinance.Core.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.homefinance.Screen.CreateProfileScreen
import com.example.homefinance.Screen.LoginScreen
import com.example.homefinance.Screen.MainScreen
import com.example.homefinance.Screen.UserScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable <Login> {
            LoginScreen(
                navigateToCreateUser = {navController.navigate(CreateProfile)},
                navigateToHome = {user -> navController.navigate(Main(userLoggedIn = user))})
        }

        composable<Main> {
            backStackEntry ->
            val userLoggedIn:Main = backStackEntry.toRoute()
            MainScreen(navigateToLogin = {navController.navigate(Login)} ,userLoggedIn = userLoggedIn.userLoggedIn)
        }

        composable<CreateProfile> {
            CreateProfileScreen(
                navigateToLogin = {navController.navigate(Login)}
            )
        }
    }
}