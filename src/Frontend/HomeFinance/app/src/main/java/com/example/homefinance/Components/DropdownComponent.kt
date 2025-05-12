package com.example.homefinance.Components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DropdownComponent(options: List<String>)
{
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    var expanded by remember { mutableStateOf(false) }
    Box(

    ) {
        TextButton(
            onClick = { expanded = !expanded },
        ) {
            Text(text = selectedOptionText)
        }
        DropdownMenu(
            modifier = Modifier.align(Alignment.Center),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach {
                    option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedOptionText = option
                        expanded = false
                    }
                )
            }
        }
    }

}