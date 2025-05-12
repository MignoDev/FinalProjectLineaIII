package com.example.homefinance.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

@Composable
fun PieChartComponent (pieChartDataList: List<PieChartData.Slice>)
{
    val pieChartData = PieChartData(
        /*
        slices = listOf(
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
                label = "rOMASNCE",
                value = 40f,
                color = Color(0xffF53844)
            )
        ),
        */
        slices = pieChartDataList,
        plotType = PlotType.Pie
    )

    val pieChartConfig = PieChartConfig(
        isAnimationEnable = true,
        showSliceLabels = true,
        activeSliceAlpha = 0.5f,
        animationDuration = 600
    )

    PieChart(
        modifier = Modifier
            .width(300.dp)
            .height(300.dp),
        pieChartData = pieChartData,
        pieChartConfig = pieChartConfig
    )
}