package com.example.homefinance.Screen

import android.annotation.SuppressLint
import android.provider.CalendarContract
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.yml.charts.common.model.Point
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.homefinance.Components.BarChartComponent
import com.example.homefinance.Components.LineChartComponent
import com.example.homefinance.Components.PieChartComponent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ResumeScreen () {

    val options = listOf("Hogar 1", "Hogar 2", "Hogar 3")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }



    Column (
        modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {}
            ) {
                Text(text= "Ingresos")
            }
            Button(
                onClick = {}
            ) {
                Text(text= "Gastos")
            }
        }
        LineChartComponent(listOf(
            Point(0f, 40f),
            Point(1f, 90f),
            Point(2f, 0f),
            Point(3f, 60f),
            Point(4f, 10f),
        ))
        PieChartComponent(listOf(

                PieChartData.Slice(
                    label = "Scifi",
                    value = 65f,
                    color = Color(0xff333333)
                ),
                PieChartData.Slice(
                    label = "Comedy",
                    value = 35f,
                    color = Color(0xff666a86)
                ),
                PieChartData.Slice(
                    label = "Drama",
                    value = 10f,
                    color = Color(0xff95B8D1)
                ),
                PieChartData.Slice(
                    label = "Romance",
                    value = 40f,
                    color = Color(0xffF53844)
                )
            )
        )
        BarChartComponent()

    }
}