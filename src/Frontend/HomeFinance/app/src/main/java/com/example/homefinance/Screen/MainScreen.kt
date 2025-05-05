package com.example.homefinance.Screen

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.provider.CalendarContract
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        1 -> MovementScreen()
        2 -> HomeScreen()
        3 -> InvestmentScreen()
        4 -> ProfileScreen()
    }
}