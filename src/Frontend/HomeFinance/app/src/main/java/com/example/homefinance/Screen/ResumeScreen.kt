package com.example.homefinance.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import co.yml.charts.common.model.Point
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.homefinance.Components.BarChartComponent
import com.example.homefinance.Components.DropdownComponent
import com.example.homefinance.Components.LineChartComponent
import com.example.homefinance.Components.PieChartComponent
import com.example.homefinance.ViewModel.UserViewModel
import kotlinx.coroutines.launch
import java.nio.file.WatchEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ResumeScreen (userName: Long, userViewModel: UserViewModel = viewModel()) {

    val options = listOf("Hogar 1", "Hogar 2", "Hogar 3")
    var selectedGraph by remember { mutableIntStateOf(0) }

    val user by userViewModel.userUnique.observeAsState(null)

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(true) {
        scope.launch {
            userViewModel.findUser(userName)
            user?.let { safeUser ->

            }
        }
    }


//region Contenedor de la pantalla
    Column (
        modifier = Modifier
            .padding(top = 40.dp, bottom = 105.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        //region selección de hogar
        //DropdownComponent(options = options)
        //endregion


        //region seleccionador de gráfico
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            TextButton(
                onClick = {
                    selectedGraph = 0
                },
                shape = RectangleShape,
                modifier = Modifier
                    .width(150.dp)
                    .background(
                        color = if(selectedGraph == 0) {
                            Color(0x22444488)
                        } else {Color(0x00ffffff)}
                    )
            ) {
                Text(text= "General",
                    fontWeight = if (selectedGraph == 0)
                        FontWeight.Medium else FontWeight.Normal,
                    style = TextStyle(fontSize =if (selectedGraph == 0) 20.sp else 16.sp))
            }
            TextButton(
                onClick = {
                    selectedGraph = 1
                },
                shape = RectangleShape,
                modifier = Modifier
                    .width(150.dp)
                    .background(
                        color = if(selectedGraph == 1) {
                            Color(0x22444488)
                        } else {Color(0x00ffffff)}
                    )
            ) {
                Text(text= "Ingresos",
                    fontWeight = if (selectedGraph == 1)
                        FontWeight.Medium else FontWeight.Normal,
                    style = TextStyle(fontSize =if (selectedGraph == 1) 20.sp else 16.sp))
            }
            TextButton(
                onClick = {
                    selectedGraph = 2
                },
                shape = RectangleShape,
                modifier = Modifier
                    .width(150.dp)
                    .background(
                        color = if(selectedGraph == 2) {
                            Color(0x22444488)
                        } else {Color(0x00ffffff)}
                    )
            ) {
                Text(text= "Gastos",
                    fontWeight = if (selectedGraph == 2)
                        FontWeight.Medium else FontWeight.Normal,
                    style = TextStyle(fontSize =if (selectedGraph == 2) 20.sp else 16.sp))
            }
            TextButton(
                onClick = {
                    selectedGraph = 3
                },
                shape = RectangleShape,
                modifier = Modifier
                    .width(150.dp)
                    .background(
                        color = if(selectedGraph == 3) {
                            Color(0x22444488)
                        } else {Color(0x00ffffff)}
                    )
            ) {
                Text(text= "Inversiones",
                    fontWeight = if (selectedGraph == 3)
                        FontWeight.Medium else FontWeight.Normal,
                    style = TextStyle(fontSize =if (selectedGraph == 3) 20.sp else 16.sp))
            }
        }
        //endregion

        //region gráficos

        LineChartComponent(listOf(
            Point(0f, 40f),
            Point(1f, 90f),
            Point(2f, -10f),
            Point(3f, 60f),
            Point(4f, 10f),
        ))
        /*
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
        */
        //endregion

        //region actualizaciones recientes
        Text(text = "Actualizaciones",
            fontWeight = FontWeight.SemiBold,
            style = TextStyle(fontSize = 22.sp))
        Card (modifier = Modifier
            .fillMaxSize(),
            )
        {
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                item{
                    Text(text = "Actividad 1",
                        style = TextStyle(fontSize = 20.sp))
                }
                item{
                    Text(text = "Actividad 2",
                        style = TextStyle(fontSize = 20.sp))
                }
                item{
                    Text(text = "Actividad 3",
                        style = TextStyle(fontSize = 20.sp))
                }
                item{
                    Text(text = "Actividad 4",
                        style = TextStyle(fontSize = 20.sp))
                }
                item{
                    Text(text = "Actividad 5",
                        style = TextStyle(fontSize = 20.sp))
                }
                item{
                    Text(text = "Actividad 6",
                        style = TextStyle(fontSize = 20.sp))
                }
                item{
                    Text(text = "Actividad 7",
                        style = TextStyle(fontSize = 20.sp))
                }
                item{
                    Text(text = "Actividad 8",
                        style = TextStyle(fontSize = 20.sp))
                }
                item{
                    Text(text = "Actividad 9",
                        style = TextStyle(fontSize = 20.sp))
                }
                item{
                    Text(text = "Actividad 10",
                        style = TextStyle(fontSize = 20.sp))
                }
            }
        }
        //enregion
    }
    //endregion
}