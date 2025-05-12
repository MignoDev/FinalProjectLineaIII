package com.example.homefinance.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homefinance.Core.Navigation.BottomNavBar
import com.example.homefinance.Core.Navigation.NavItemList

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navigateToSettings: () -> Unit) {
    var selectedIndex by remember { mutableIntStateOf(0) }

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
        ContentScreen(selectedIndex)
    }
}


@Composable
fun ContentScreen(selectedIndex: Int) {
    when(selectedIndex) {
        0 -> ResumeScreen()
        1 -> UserScreen()
        2 -> HomeScreen()
        3 -> InvestmentScreen()
        4 -> ProfileScreen()
    }
}