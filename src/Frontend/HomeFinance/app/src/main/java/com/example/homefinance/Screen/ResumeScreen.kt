package com.example.homefinance.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import co.yml.charts.common.model.Point
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.homefinance.Components.BarChartComponent
import com.example.homefinance.Components.DropdownComponent
import com.example.homefinance.Components.LineChartComponent
import com.example.homefinance.Components.PieChartComponent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ResumeScreen () {

    val options = listOf("Hogar 1", "Hogar 2", "Hogar 3")



    Column (
        modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DropdownComponent(options = options)

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            TextButton(
                onClick = {},
                shape = RectangleShape,
                modifier = Modifier
                    .width(100.dp)
            ) {
                Text(text= "General")
            }
            TextButton(
                onClick = {},
                shape = RectangleShape,
                modifier = Modifier
                    .width(100.dp)
            ) {
                Text(text= "Ingresos")
            }
            TextButton(
                onClick = {},
                shape = RectangleShape,
                modifier = Modifier
                    .width(100.dp)
            ) {
                Text(text= "Gastos")
            }
            TextButton(
                onClick = {},
                shape = RectangleShape,
                modifier = Modifier
                    .width(100.dp)
            ) {
                Text(text= "Inversiones")
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