package com.example.homefinance.Core.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.DataThresholding
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material.icons.filled.Person
import com.example.homefinance.Model.NavItem

object NavItemList {

    val navItemList = listOf(
        NavItem("Resumen", Icons.Default.Analytics),
        NavItem("Movimientos", Icons.Default.CurrencyExchange),
        NavItem("Hogares", Icons.Default.HomeWork),
        NavItem("Inversiones", Icons.Default.DataThresholding),
        NavItem("Perfil", Icons.Default.Person)
    )
}