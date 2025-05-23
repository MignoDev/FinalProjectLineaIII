package com.example.homefinance.Screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homefinance.Model.Income
import com.example.homefinance.Model.IncomeCreate
import com.example.homefinance.Model.Investment
import com.example.homefinance.Model.InvestmentCreate
import com.example.homefinance.Model.PlannedExpense
import com.example.homefinance.Model.PlannedExpenseCreate
import com.example.homefinance.Model.PlannedExpenseDetail
import com.example.homefinance.Model.PlannedExpenseDetailCreate
import com.example.homefinance.Model.PlannedExpenseWithDetailDTO
import com.example.homefinance.ViewModel.HomeUserViewModel
import com.example.homefinance.ViewModel.HomeViewModel
import com.example.homefinance.ViewModel.IncomeViewModel
import com.example.homefinance.ViewModel.InvestmentViewModel
import com.example.homefinance.ViewModel.PlannedExpenseDetailViewModel
import com.example.homefinance.ViewModel.PlannedExpenseViewModel
import com.example.homefinance.ViewModel.UserViewModel
import java.time.LocalDate

@Composable
fun HomeScreen (userName: Long,
                investmentViewModel: InvestmentViewModel = viewModel(),
                incomeViewModel: IncomeViewModel = viewModel(),
                expenseViewModel: PlannedExpenseViewModel = viewModel(),
                userViewModel: UserViewModel = viewModel(),
                homeUserViewModel: HomeUserViewModel = viewModel(),
                detailExpense: PlannedExpenseDetailViewModel = viewModel()) {

    val investments by investmentViewModel.investment.observeAsState(emptyList())
    val income by incomeViewModel.income.observeAsState(emptyList())
    val expense by expenseViewModel.plannedExpense.observeAsState(emptyList())
    val expenseCreate by expenseViewModel.plannedExpenseCreate.observeAsState(null)
    val user by userViewModel.userUnique.observeAsState(null)
    val home by homeUserViewModel.homeUserUnique.observeAsState(null)
    val fullExpense by expenseViewModel.plannedExpenseFull.observeAsState(emptyList())

    var showDialogIngreso by remember { mutableStateOf(false) }
    var showDialogGasto by remember { mutableStateOf(false) }
    var showDialogInversion by remember { mutableStateOf(false) }


    var ingresoAEliminar by remember { mutableStateOf<Income?>(null) }
    var gastoAEliminar by remember { mutableStateOf<PlannedExpenseWithDetailDTO?>(null) }
    var inversionAEliminar by remember { mutableStateOf<Investment?>(null) }


    var inputOption by remember { mutableStateOf(0)}
    var inputNombre by remember { mutableStateOf("") }
    var inputValor by remember { mutableStateOf("") }
    var inputComment by remember { mutableStateOf("")}

    var incomeTrigger by remember { mutableStateOf(false) }
    var expenseTrigger by remember { mutableStateOf(false) }
    var investmentTrigger by remember { mutableStateOf(false) }

    var showOption by remember { mutableStateOf(0)}

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    //Se obtiene el homeUser al cargar la ventana
    LaunchedEffect(true) {
        homeUserViewModel.findHomeUserByUserId(userName)
    }


    //Se obtiene la informacion del usuario al cambiar el usuario
    LaunchedEffect(userName) {
        userViewModel.findUser(userName)
    }

    //Se carga el ingreso siempre que halla un cambio en el home e incomeTrigger sea true
    LaunchedEffect(home) {
        if(incomeTrigger)
        {
            home?.let { safeHome ->
                val income = IncomeCreate(amount = inputValor.toDouble(),
                    homeId = safeHome.homeId,
                    typeIncomeId = 1,
                    description = inputNombre,
                    date = LocalDate.now().toString())
                incomeViewModel.createIncome(income)
                inputNombre = ""
                inputComment = ""
                inputValor = ""
                incomeTrigger = false
            }

        }
    }

    //Se carga el Gast siempre que halla un cambio en el home y expenseTrigger sea true
    LaunchedEffect(home) {
        if(expenseTrigger)
        {
            home?.let { safeHome ->
                val expense = PlannedExpenseCreate(
                    homeId = safeHome.homeId,
                    description = inputNombre,
                    amount = inputValor.toDouble(),
                    comment = inputComment,
                    typeId = 1)
                expenseViewModel.createPlannedExpense(expense)
            }
        }
    }

    //Se carga el Detalle del gasto siempre que halla un cambio en el expenseCreate y expense Trigger sea true
    LaunchedEffect(expenseCreate) {
        if (expenseTrigger)
        {
            expenseCreate?.let { safeExpense ->
                val plannedExpense = PlannedExpenseDetailCreate(
                    plannedExpenseId = safeExpense.id,
                    date = LocalDate.now().toString())
                detailExpense.createPlannedExpenseDetail(plannedExpense)
                inputNombre = ""
                inputComment = ""
                inputValor = ""
                expenseTrigger = false
            }
        }
    }

    //Se carga la inversión siempre que halla un cambio en el home e investmentTrigger sea true
    LaunchedEffect(home) {
        if(investmentTrigger)
        {
            home?.let { safeHome ->
                val investment = InvestmentCreate(
                    homeId = safeHome.homeId,
                    description = inputNombre,
                    amount = inputValor.toDouble(),
                    date = LocalDate.now().toString())
                investmentViewModel.createInvestment(investment)
                inputNombre = ""
                inputComment = ""
                inputValor = ""
                investmentTrigger = false
            }

        }
    }



    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 45.dp)
    ) {
        Text(text = "Crear registros")

        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    inputNombre = ""
                    inputValor = ""
                    inputOption = 1
                }
            ) {
                Text(text = "Ingresos")
            }
            Button(
                onClick = {
                    inputNombre = ""
                    inputValor = ""
                    inputOption = 2
                }
            ) {
                Text(text = "Gastos")
            }
            Button(
                onClick = {
                    inputNombre = ""
                    inputValor = ""
                    inputOption = 3
                }
            ) {
                Text(text = "Inversiones")
            }

        }
        LazyColumn {
            item {
                when(inputOption) {
                    1 -> {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            TextField(
                                value = inputNombre,
                                onValueChange = { newValue ->
                                    inputNombre = newValue
                                },
                                label = { Text("Nombre del ingreso") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            TextField(
                                value = inputValor,
                                onValueChange = { newValue ->
                                    // Permitir solo números
                                    if (newValue.all { it.isDigit() }) {
                                        inputValor = newValue
                                    }
                                },
                                label = { Text("Valor del ingreso") },
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )

                            OutlinedButton(
                                onClick = {
                                    incomeTrigger = true
                                    homeUserViewModel.findHomeUserByUserId(userName)

                                    inputOption = 0
                                }
                            ) {
                                Text(text = "Confirmar ingreso")
                            }
                        }
                    }

                    2 -> {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            TextField(
                                value = inputNombre,
                                onValueChange = { newValue ->
                                    inputNombre = newValue
                                },
                                label = { Text("Nombre del Gasto") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            TextField(
                                value = inputValor,
                                onValueChange = { newValue ->
                                    // Permitir solo números
                                    if (newValue.all { it.isDigit() }) {
                                        inputValor = newValue
                                    }
                                },
                                label = { Text("Valor del Gasto") },
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                            TextField(
                                value = inputComment,
                                onValueChange = { newValue ->
                                    inputComment = newValue
                                },
                                label = { Text("Comentario") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedButton(
                                onClick = {

                                    expenseTrigger = true
                                    homeUserViewModel.findHomeUserByUserId(userName)
                                    inputOption = 0
                                }
                            ) {
                                Text(text = "Confirmar Gasto")
                            }
                        }
                    }

                    3 -> {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            TextField(
                                value = inputNombre,
                                onValueChange = { newValue ->
                                    // Permitir solo números
                                    inputNombre = newValue
                                },
                                modifier = Modifier.fillMaxWidth(),
                                label = { Text("Nombre de la inversión") },
                            )

                            TextField(
                                value = inputValor,
                                onValueChange = { newValue ->
                                    // Permitir solo números
                                    if (newValue.all { it.isDigit() }) {
                                        inputValor = newValue
                                    }
                                },
                                label = { Text("Valor de la inversión") },
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedButton(
                                onClick = {
                                    investmentTrigger = true
                                    homeUserViewModel.findHomeUserByUserId(userName)
                                    inputOption = 0
                                }
                            ) {
                                Text(text = "Confirmar Inversion")
                            }
                        }
                    }
                }
            }

            item {
                Text(text = "Mostrar registros")
                Row (
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            incomeViewModel.findIncomeByHomeId(home!!.homeId)
                            showOption = 1
                        }
                    ) {
                        Text(text = "Ingresos")
                    }
                    Button(
                        onClick = {
                            expenseViewModel.findFullExpense(home!!.homeId)
                            showOption = 2
                        }
                    ) {
                        Text(text = "Gastos")
                    }
                    Button(
                        onClick = {
                            investmentViewModel.findInvestmentByHomeId(home!!.homeId)
                            showOption = 3
                        }
                    ) {
                        Text(text = "Inversiones")
                    }
                }
            }
            item {
                when (showOption) {
                    1 -> {
                        Column (
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
                                        Column (verticalArrangement = Arrangement.Center) {
                                            Row (horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                Text(text = "Ingreso: ",
                                                    style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                    fontSize = 17.sp,
                                                    modifier = Modifier.align(alignment = Alignment.CenterVertically))
                                                Text(text = ingreso.description)
                                            }
                                            Row (horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                Text(text = "Monto: ",
                                                    style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                    fontSize = 17.sp,
                                                    modifier = Modifier.align(alignment = Alignment.CenterVertically))
                                                Text(text = "${ingreso.amount}\$")
                                            }
                                            Row (horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                Text(text = "Fecha de creación: ",
                                                    style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                    fontSize = 17.sp,
                                                    modifier = Modifier.align(alignment = Alignment.CenterVertically))
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
                                        Column (verticalArrangement = Arrangement.Center) {
                                            Row (horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                Text(text = "Gasto: ",
                                                    style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                    fontSize = 17.sp,
                                                    modifier = Modifier.align(alignment = Alignment.CenterVertically))
                                                Text(text = gasto.plannedExpense.description)
                                            }
                                            Row (horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                Text(text = "Monto: ",
                                                    style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                    fontSize = 17.sp,
                                                    modifier = Modifier.align(alignment = Alignment.CenterVertically))
                                                Text(text = "${gasto.plannedExpense.amount}\$")
                                            }
                                            Row (horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                Text(text = "Fecha de creación: ",
                                                    style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                    fontSize = 17.sp,
                                                    modifier = Modifier.align(alignment = Alignment.CenterVertically))
                                                Text(text = "${gasto.plannedExpenseDetail.date}")
                                            }
                                            if(gasto.plannedExpense.comment != "")
                                                Row (horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                    Text(text = "Comentario: ",
                                                        style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                        fontSize = 17.sp,
                                                        modifier = Modifier.align(alignment = Alignment.CenterVertically))
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
                                        Column (verticalArrangement = Arrangement.Center) {
                                            Row (horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                Text(text = "Inversión: ",
                                                    style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                    fontSize = 17.sp,
                                                    modifier = Modifier.align(alignment = Alignment.CenterVertically))
                                                Text(text = inversion.description)
                                            }
                                            Row (horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                Text(text = "Monto: ",
                                                    style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                    fontSize = 17.sp,
                                                    modifier = Modifier.align(alignment = Alignment.CenterVertically))
                                                Text(text = "${inversion.amount}\$")
                                            }
                                            Row (horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                                Text(text = "Fecha de creación: ",
                                                    style = TextStyle(fontWeight = FontWeight.SemiBold),
                                                    fontSize = 17.sp,
                                                    modifier = Modifier.align(alignment = Alignment.CenterVertically))
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
                                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
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
                                incomeViewModel.listIncomes()
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
                                expenseViewModel.deletePlannedExpense(it.plannedExpense.id)
                                // Mostrar el Toast de éxito
                                Toast.makeText(context, "Ingreso eliminado", Toast.LENGTH_SHORT).show()
                                expenseViewModel.listPlannedExpenses()
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
                                investmentViewModel.listInvestments()
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
    }
}