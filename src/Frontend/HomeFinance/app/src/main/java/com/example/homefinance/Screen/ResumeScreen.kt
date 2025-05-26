package com.example.homefinance.Screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
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
import com.example.homefinance.Model.Income
import com.example.homefinance.Model.Investment
import com.example.homefinance.Model.PlannedExpenseWithDetailDTO
import com.example.homefinance.ViewModel.HomeUserViewModel
import com.example.homefinance.ViewModel.IncomeViewModel
import com.example.homefinance.ViewModel.InvestmentViewModel
import com.example.homefinance.ViewModel.PlannedExpenseDetailViewModel
import com.example.homefinance.ViewModel.PlannedExpenseViewModel
import com.example.homefinance.ViewModel.UserViewModel
import kotlinx.coroutines.launch
import java.nio.file.WatchEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ResumeScreen(
    userName: Long,
    investmentViewModel: InvestmentViewModel = viewModel(),
    incomeViewModel: IncomeViewModel = viewModel(),
    expenseViewModel: PlannedExpenseViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel(),
    homeUserViewModel: HomeUserViewModel = viewModel(),
    detailExpense: PlannedExpenseDetailViewModel = viewModel()
) {



    val homeUser by homeUserViewModel.homeUserUnique.observeAsState(null)
    val user by userViewModel.userUnique.observeAsState(null)
    val income by incomeViewModel.income.observeAsState(emptyList())
    val investments by investmentViewModel.investment.observeAsState(emptyList())
    val fullExpense by expenseViewModel.plannedExpenseFull.observeAsState(emptyList())
    val deletedExpense by detailExpense.deleted.observeAsState(null)


    var showOption by remember { mutableStateOf(0) }

    var showDialogIngreso by remember { mutableStateOf(false) }
    var showDialogGasto by remember { mutableStateOf(false) }
    var showDialogInversion by remember { mutableStateOf(false) }

    var ingresoAEliminar by remember { mutableStateOf<Income?>(null) }
    var gastoAEliminar by remember { mutableStateOf<PlannedExpenseWithDetailDTO?>(null) }
    var inversionAEliminar by remember { mutableStateOf<Investment?>(null) }
    var deletedExpenseUpdate by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var incomeList by remember { mutableStateOf<List<Income>?>(emptyList()) }

    LaunchedEffect(true) {
        scope.launch {
            userViewModel.findUser(userName)
            user?.let { safeUser ->
                homeUserViewModel.findHomeUserByUserId(safeUser.id)
            }
        }
    }

    LaunchedEffect(true) {
        homeUserViewModel.findHomeUserByUserId(userName)
    }


    //Se obtiene la informacion del usuario al cambiar el usuario
    LaunchedEffect(userName) {
        userViewModel.findUser(userName)
    }

    LaunchedEffect(deletedExpense) {
        if (deletedExpense != null) {
            expenseViewModel.deletePlannedExpense(deletedExpense!!)
            deletedExpenseUpdate = true
        }
    }

    LaunchedEffect(deletedExpense) {
        if (deletedExpenseUpdate)
        {
            expenseViewModel.findFullExpense(homeUser!!.homeId)
        }
    }

    fun generateDonutChartSlices(incomes: List<Income>): List<PieChartData.Slice> {
        val colors = listOf(
            Color(0xffF94144),
            Color(0xffF3722C),
            Color(0xffF9C74F),
            Color(0xff90BE6D),
            Color(0xff43AA8B),
            Color(0xff577590)
        )

        return incomes
            .groupBy { it.description } // Agrupa por categoría
            .entries
            .mapIndexed { index, entry ->
                val totalAmount = entry.value.sumOf { it.amount }.toFloat()
                PieChartData.Slice(
                    label = entry.key,
                    value = totalAmount,
                    color = colors[index % colors.size]
                )
            }
    }


//region Contenedor de la pantalla
    Column(
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            TextButton(
                onClick = {
                    showOption = 0
                },
                shape = RectangleShape,
                modifier = Modifier
                    .width(150.dp)
                    .background(
                        color = if (showOption == 0) {
                            Color(0x22444488)
                        } else {
                            Color(0x00ffffff)
                        }
                    )
            ) {
                Text(
                    text = "General",
                    fontWeight = if (showOption == 0)
                        FontWeight.Medium else FontWeight.Normal,
                    style = TextStyle(fontSize = if (showOption == 0) 20.sp else 16.sp)
                )
            }
            TextButton(
                onClick = {
                    incomeViewModel.findIncomeByHomeId(homeUser!!.homeId)
                    showOption = 1
                },
                shape = RectangleShape,
                modifier = Modifier
                    .width(150.dp)
                    .background(
                        color = if (showOption == 1) {
                            Color(0x22444488)
                        } else {
                            Color(0x00ffffff)
                        }
                    )
            ) {
                Text(
                    text = "Ingresos",
                    fontWeight = if (showOption == 1)
                        FontWeight.Medium else FontWeight.Normal,
                    style = TextStyle(fontSize = if (showOption == 1) 20.sp else 16.sp)
                )
            }
            TextButton(
                onClick = {
                    expenseViewModel.findFullExpense(homeUser!!.homeId)
                    showOption = 2
                },
                shape = RectangleShape,
                modifier = Modifier
                    .width(150.dp)
                    .background(
                        color = if (showOption == 2) {
                            Color(0x22444488)
                        } else {
                            Color(0x00ffffff)
                        }
                    )
            ) {
                Text(
                    text = "Gastos",
                    fontWeight = if (showOption == 2)
                        FontWeight.Medium else FontWeight.Normal,
                    style = TextStyle(fontSize = if (showOption == 2) 20.sp else 16.sp)
                )
            }
            TextButton(
                onClick = {
                    investmentViewModel.findInvestmentByHomeId(homeUser!!.homeId)
                    showOption = 3
                },
                shape = RectangleShape,
                modifier = Modifier
                    .width(150.dp)
                    .background(
                        color = if (showOption == 3) {
                            Color(0x22444488)
                        } else {
                            Color(0x00ffffff)
                        }
                    )
            ) {
                Text(
                    text = "Inversiones",
                    fontWeight = if (showOption == 3)
                        FontWeight.Medium else FontWeight.Normal,
                    style = TextStyle(fontSize = if (showOption == 3) 20.sp else 16.sp)
                )
            }
        }
        //endregion

        //region gráficos

        LineChartComponent(
            listOf(
                Point(0f, 40f),
                Point(1f, 90f),
                Point(2f, -10f),
                Point(3f, 60f),
                Point(4f, 10f),
            )
        )

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
        Text(
            text = "Actualizaciones",
            fontWeight = FontWeight.SemiBold,
            style = TextStyle(fontSize = 22.sp)
        )
        Card(
            modifier = Modifier
                .fillMaxSize(),
        )
        {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                item {
                    when (showOption) {
                        1 -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                            {
                                income!!.forEach { ingreso ->
                                    Card()
                                    {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(8.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Column(verticalArrangement = Arrangement.Center) {
                                                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                    Text(
                                                        text = "Ingreso: ",
                                                        style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                        fontSize = 17.sp,
                                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                                    )
                                                    Text(text = ingreso.description)
                                                }
                                                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                    Text(
                                                        text = "Monto: ",
                                                        style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                        fontSize = 17.sp,
                                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                                    )
                                                    Text(text = "${ingreso.amount}\$")
                                                }
                                                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                    Text(
                                                        text = "Fecha de creación: ",
                                                        style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                        fontSize = 17.sp,
                                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                                    )
                                                    Text(text = "${ingreso.date}")
                                                }

                                            }
                                            Button(
                                                onClick = {
                                                    // Mostrar el diálogo de confirmación de eliminación

                                                    ingresoAEliminar = ingreso
                                                    showDialogIngreso = true
                                                },
                                            ) {
                                                Icon(
                                                    Icons.Filled.Delete,
                                                    contentDescription = "Eliminar ingreso Icon"
                                                )
                                            }
                                        }
                                    }

                                }
                            }
                        }

                        2 -> {
                            Column {
                                fullExpense!!.forEach { gasto ->
                                    Card {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(8.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Column(verticalArrangement = Arrangement.Center) {
                                                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                    Text(
                                                        text = "Gasto: ",
                                                        style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                        fontSize = 17.sp,
                                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                                    )
                                                    Text(text = gasto.plannedExpense.description)
                                                }
                                                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                    Text(
                                                        text = "Monto: ",
                                                        style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                        fontSize = 17.sp,
                                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                                    )
                                                    Text(text = "${gasto.plannedExpense.amount}\$")
                                                }
                                                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                    Text(
                                                        text = "Fecha de creación: ",
                                                        style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                        fontSize = 17.sp,
                                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                                    )
                                                    Text(text = "${gasto.plannedExpenseDetail.date}")
                                                }
                                                if (gasto.plannedExpense.comment != "")
                                                    Row(
                                                        horizontalArrangement = Arrangement.spacedBy(
                                                            2.dp
                                                        )
                                                    ) {
                                                        Text(
                                                            text = "Comentario: ",
                                                            style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                            fontSize = 17.sp,
                                                            modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                                        )
                                                        Text(text = gasto.plannedExpense.comment)
                                                    }

                                            }
                                            Button(
                                                onClick = {
                                                    // Mostrar el diálogo de confirmación de eliminación
                                                    gastoAEliminar = gasto
                                                    showDialogGasto = true
                                                },
                                            ) {
                                                Icon(
                                                    Icons.Filled.Delete,
                                                    contentDescription = "Eliminar Gasto Icon"
                                                )
                                            }
                                        }
                                    }

                                }
                            }
                        }

                        3 -> {
                            Column {
                                investments!!.forEach { inversion ->
                                    Card {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(8.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Column(verticalArrangement = Arrangement.Center) {
                                                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                    Text(
                                                        text = "Inversión: ",
                                                        style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                        fontSize = 17.sp,
                                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                                    )
                                                    Text(text = inversion.description)
                                                }
                                                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                    Text(
                                                        text = "Monto: ",
                                                        style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                        fontSize = 17.sp,
                                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                                    )
                                                    Text(text = "${inversion.amount}\$")
                                                }
                                                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                    Text(
                                                        text = "Fecha de creación: ",
                                                        style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                        fontSize = 17.sp,
                                                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                                    )
                                                    Text(text = "${inversion.date}")
                                                }

                                            }
                                            Button(
                                                onClick = {
                                                    // Mostrar el diálogo de confirmación de eliminación
                                                    inversionAEliminar = inversion
                                                    showDialogInversion = true
                                                },
                                                modifier = Modifier.padding(top = 8.dp),
                                                contentPadding = PaddingValues(
                                                    horizontal = 16.dp,
                                                    vertical = 8.dp
                                                )
                                            ) {
                                                Icon(
                                                    Icons.Filled.Delete,
                                                    contentDescription = "Eliminar Inversión Icon"
                                                )
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
            //enregion
        }
        //endregion
        //region dialogs
        if (showDialogIngreso) {
            AlertDialog(
                onDismissRequest = { showDialogIngreso = false },
                title = { Text("Confirmación") },
                text = { Text("¿Está seguro que desea eliminar a este usuario?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            // Eliminar el estudiante si el usuario confirma
                            ingresoAEliminar?.let {
                                incomeViewModel.deleteIncome(it.id)
                                // Mostrar el Toast de éxito
                                Toast.makeText(context, "Ingreso eliminado", Toast.LENGTH_SHORT).show()
                                incomeViewModel.findIncomeByHomeId(homeUser!!.homeId)
                            }
                            showDialogIngreso = false
                        }
                    ) {
                        Text("Sí")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showDialogIngreso = false }
                    ) {
                        Text("No")
                    }
                }
            )
        }

        if (showDialogGasto) {
            AlertDialog(
                onDismissRequest = { showDialogGasto = false },
                title = { Text("Confirmación") },
                text = { Text("¿Está seguro que desea eliminar a este usuario?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            // Eliminar el estudiante si el usuario confirma
                            gastoAEliminar?.let {
                                detailExpense.deletePlannedExpenseDetail(it.plannedExpenseDetail.id)

                                // Mostrar el Toast de éxito
                                Toast.makeText(context, "Ingreso eliminado", Toast.LENGTH_SHORT).show()
                            }
                            showDialogGasto = false
                        }
                    ) {
                        Text("Sí")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showDialogIngreso = false }
                    ) {
                        Text("No")
                    }
                }
            )
        }

        if (showDialogInversion) {
            AlertDialog(
                onDismissRequest = { showDialogInversion = false },
                title = { Text("Confirmación") },
                text = { Text("¿Está seguro que desea eliminar a este usuario?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            // Eliminar el estudiante si el usuario confirma
                            inversionAEliminar?.let {
                                investmentViewModel.deleteInvestment(it.id)
                                // Mostrar el Toast de éxito
                                Toast.makeText(context, "Ingreso eliminado", Toast.LENGTH_SHORT).show()
                                investmentViewModel.findInvestmentByHomeId(homeUser!!.homeId)
                            }
                            showDialogInversion = false
                        }
                    ) {
                        Text("Sí")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showDialogInversion = false }
                    ) {
                        Text("No")
                    }
                }
            )
        }
        //endregion
    }
}