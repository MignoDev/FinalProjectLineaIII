package com.example.homefinance

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.homefinance.Core.Navigation.AppNavGraph
import com.example.homefinance.Core.Navigation.BottomNavBar
import com.example.homefinance.Core.Navigation.NavItemList
import com.example.homefinance.Screen.BottomNavScreen
import com.example.homefinance.Screen.HomeScreen
import com.example.homefinance.Screen.InvestmentScreen
import com.example.homefinance.Screen.MainScreen
import com.example.homefinance.Screen.MovementScreen
import com.example.homefinance.Screen.ProfileScreen
import com.example.homefinance.Screen.ResumeScreen
import com.example.homefinance.Screen.UserScreen
import com.example.homefinance.ui.theme.HomeFinanceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            HomeFinanceTheme {
                MainScreen(navController.navigate(settings))
            }
        }
    }
}

