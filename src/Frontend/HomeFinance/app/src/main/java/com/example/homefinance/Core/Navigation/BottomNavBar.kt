package com.example.homefinance.Core.Navigation

import android.icu.text.AlphabeticIndex
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homefinance.Model.NavItem

@Composable
fun BottomNavBar (
    navItemList: List<NavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar {
        navItemList.forEachIndexed { index, navItem ->
            if (navItem.label != "")
            {
                NavigationBarItem(
                    selected = selectedIndex == index,
                    onClick = {
                        onItemSelected(index)
                    },
                    icon = {
                        Icon(imageVector = navItem.icon, contentDescription = "Icon")
                    },
                    label = { Text(text = navItem.label) }

                    )
            } else {
                NavigationBarItem(
                    selected = selectedIndex == index,
                    onClick = {
                        onItemSelected(index)
                    },
                    icon = {
                        Icon(imageVector = navItem.icon, contentDescription = "Icon", modifier = Modifier.size(35.dp))
                    }
                )
            }

        }
    }
}