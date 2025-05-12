package com.example.homefinance.Components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

@Composable
fun DonutChartComponent (donutChartDataList: List<PieChartData.Slice>)
{
    val donutChartData = PieChartData(
        slices = donutChartDataList,
        plotType = PlotType.Pie
    )

    val donutChartConfig = PieChartConfig(
        isAnimationEnable = true,
        showSliceLabels = true,
        activeSliceAlpha = 0.5f,
        animationDuration = 600
    )

    PieChart(
        modifier = Modifier
            .width(300.dp)
            .height(300.dp),
        pieChartData = donutChartData,
        pieChartConfig = donutChartConfig
    )
}