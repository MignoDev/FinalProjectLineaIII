package com.example.homefinance.Core.Navigation

import android.icu.text.AlphabeticIndex
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.homefinance.Model.NavItem

@Composable
fun BottomNavBar (
    navItemList: List<NavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar {
        navItemList.forEachIndexed { index, navItem ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    onItemSelected(index)
                },
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = "Icon")
                },
                label = {
                    Text(text = navItem.label)
                }
            )
        }
    }
}