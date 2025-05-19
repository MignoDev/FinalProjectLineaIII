package com.example.homefinance.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homefinance.Core.Navigation.BottomNavBar
import com.example.homefinance.Core.Navigation.NavItemList

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navigateToLogin: () -> Unit, userLoggedIn: Long) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val userLoggedIn = userLoggedIn

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(
                navItemList = NavItemList.navItemList,
                selectedIndex = selectedIndex,
                onItemSelected = { index -> selectedIndex = index}

            )
        }
    ) {
        ContentScreen(selectedIndex, userLoggedIn)
    }
}


@Composable
fun ContentScreen(selectedIndex: Int, user: Long) {
    when(selectedIndex) {
        0 -> ResumeScreen(user)
        //1 -> UserScreen(userLogged = user)
        1 -> HomeScreen(user)
        //3 -> InvestmentScreen(user)
        2 -> ProfileScreen(user)
    }
}