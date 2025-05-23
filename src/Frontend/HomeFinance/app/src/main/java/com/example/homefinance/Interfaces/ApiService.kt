package com.example.homefinance.Interfaces

import com.example.homefinance.Model.ActualExpense
import com.example.homefinance.Model.EarnInvestment
import com.example.homefinance.Model.ExpenseMatching
import com.example.homefinance.Model.Home
import com.example.homefinance.Model.HomeCreate
import com.example.homefinance.Model.HomeUser
import com.example.homefinance.Model.HomeUserCreate
import com.example.homefinance.Model.Income
import com.example.homefinance.Model.IncomeCreate
import com.example.homefinance.Model.Investment
import com.example.homefinance.Model.InvestmentCreate
import com.example.homefinance.Model.LoginRequest
import com.example.homefinance.Model.PlannedExpense
import com.example.homefinance.Model.PlannedExpenseCreate
import com.example.homefinance.Model.PlannedExpenseDetail
import com.example.homefinance.Model.PlannedExpenseDetailCreate
import com.example.homefinance.Model.PlannedExpenseWithDetailDTO
import com.example.homefinance.Model.TypeExpense
import com.example.homefinance.Model.TypeIncome
import com.example.homefinance.Model.User
import com.example.homefinance.Model.UserRequest
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    //region actualexpense Service

    @GET("actualexpense/")
    suspend fun listActualExpense(): List<ActualExpense>

    @GET("actualexpense/{id}")
    suspend fun findActualExpense(@Path("id") id: Long): ActualExpense

    @POST("actualexpense/")
    suspend fun createActualExpense(@Body input: ActualExpense)

    @PUT("actualexpense/{id}")
    suspend fun updateActualExpense(@Path("id") id: Long, @Body input: ActualExpense)

    @DELETE("actualexpense/{id}")
    suspend fun deleteActualExpense(@Path("id") id: Long)

    //endregion

    //region earninvestment Service

    @GET("earninvestment/")
    suspend fun listEarnInvestment(): List<EarnInvestment>

    @GET("earninvestment/{id}")
    suspend fun findEarnInvestment(@Path("id") id: Long): EarnInvestment

    @POST("actualexpense/")
    suspend fun createEarnInvestment(@Body input: EarnInvestment)

    @PUT("earninvestment/{id}")
    suspend fun updateEarnInvestment(@Path("id") id: Long, @Body input: EarnInvestment)

    @DELETE("earninvestment/{id}")
    suspend fun deleteEarnInvestment(@Path("id") id: Long)

    //endregion

    //region expensematching Service

    @GET("expensematching/")
    suspend fun listExpenseMatching(): List<ExpenseMatching>

    @GET("expensematching/{id}")
    suspend fun findExpenseMatching(@Path("id") id: Long): ExpenseMatching

    @POST("expensematching/")
    suspend fun createExpenseMatching(@Body input: ExpenseMatching)

    @PUT("expensematching/{id}")
    suspend fun updateExpenseMatching(@Path("id") id: Long, @Body input: ExpenseMatching)

    @DELETE("expensematching/{id}")
    suspend fun deleteExpenseMatching(@Path("id") id: Long)

    //endregion

    //region home Service

    @GET("home/")
    suspend fun listHome(): List<Home>

    @GET("home/{id}")
    suspend fun findHome(@Path("id") id: Long): Response<Home>

    @POST("home/")
    suspend fun createHome(@Body input: HomeCreate) : Home

    @PUT("home/{id}")
    suspend fun updateHome(@Path("id") id: Long, @Body input: Home)

    @DELETE("home/{id}")
    suspend fun deleteHome(@Path("id") id: Long)

    //endregion

    //region homeuser Service

    @GET("homeuser/")
    suspend fun listHomeUser(): List<HomeUser>

    @GET("homeuser/{id}")
    suspend fun findHomeUser(@Path("id") id: Long): HomeUser

    @GET("homeuser/user/{userId}")
    suspend fun findHomeUserByUserId(@Path("userId") userId: Long) : HomeUser

    @POST("homeuser/")
    suspend fun createHomeUser(@Body input: HomeUserCreate)

    @PUT("homeuser/{id}")
    suspend fun updateHomeUser(@Path("id") id: Long, @Body input: HomeUser)

    @DELETE("homeuser/{id}")
    suspend fun deleteHomeUser(@Path("id") id: Long)

    //endregion

    //region income Service

    @GET("income/")
    suspend fun listIncome(): List<Income>

    @GET("income/{id}")
    suspend fun findIncome(@Path("id") id: Long): Income

    @GET("income/home/{id}")
    suspend fun findIncomeByHomeId(@Path("id") id: Long): List<Income>

    @POST("income/")
    suspend fun createIncome(@Body input: IncomeCreate)

    @PUT("income/{id}")
    suspend fun updateIncome(@Path("id") id: Long, @Body input: Income)

    @DELETE("income/{id}")
    suspend fun deleteIncome(@Path("id") id: Long)

    //endregion

    //region investment Service

    @GET("investment/")
    suspend fun listInvestment(): List<Investment>

    @GET("investment/{id}")
    suspend fun findInvestment(@Path("id") id: Long): Investment

    @GET("investment/home/{id}")
    suspend fun findInvestmentByHomeId(@Path("id") id: Long): List<Investment>

    @POST("investment/")
    suspend fun createInvestment(@Body input: InvestmentCreate)

    @PUT("investment/{id}")
    suspend fun updateInvestment(@Path("id") id: Long, @Body input: Investment)

    @DELETE("investment/{id}")
    suspend fun deleteInvestment(@Path("id") id: Long)

    //endregion

    //region plannedexpense Service

    @GET("plannedexpense/")
    suspend fun listPlannedExpense(): List<PlannedExpense>

    @GET("plannedexpense/{id}")
    suspend fun findPlannedExpense(@Path("id") id: Long): PlannedExpense

    @GET("plannedexpense/home/{id}")
    suspend fun findPlannedExpenseByHomeId(@Path("id") id: Long): List<PlannedExpense>

    @GET("plannedexpense/full/{id}")
    suspend fun findAllExpensesById(@Path("id")id: Long): List<PlannedExpenseWithDetailDTO>

    @POST("plannedexpense/")
    suspend fun createPlannedExpense(@Body input: PlannedExpenseCreate): PlannedExpense

    @PUT("plannedexpense/{id}")
    suspend fun updatePlannedExpense(@Path("id") id: Long, @Body input: PlannedExpense)

    @DELETE("plannedexpense/{id}")
    suspend fun deletePlannedExpense(@Path("id") id: Long)

    //endregion

    //region plannedexpensedetail Service

    @GET("plannedexpensedetail/")
    suspend fun listPlannedExpenseDetail(): List<PlannedExpenseDetail>

    @GET("plannedexpensedetail/{id}")
    suspend fun findPlannedExpenseDetail(@Path("id") id: Long): PlannedExpenseDetail

    @GET("plannedexpensedetail/planned/{id}")
    suspend fun findPlannedExpenseDetailByPlannedExpenseId(@Path("id") id: Long): PlannedExpenseDetail

    @POST("plannedexpensedetail/")
    suspend fun createPlannedExpenseDetail(@Body input: PlannedExpenseDetailCreate)

    @PUT("plannedexpensedetail/{id}")
    suspend fun updatePlannedExpenseDetail(@Path("id") id: Long, @Body input: PlannedExpenseDetail)

    @DELETE("plannedexpensedetail/{id}")
    suspend fun deletePlannedExpenseDetail(@Path("id") id: Long)

    //endregion

    //region typeexpense Service

    @GET("typeexpense/")
    suspend fun listTypeExpense(): List<TypeExpense>

    @GET("typeexpense/{id}")
    suspend fun findTypeExpense(@Path("id") id: Long): TypeExpense

    @POST("typeexpense/")
    suspend fun createTypeExpense(@Body input: TypeExpense)

    @PUT("typeexpense/{id}")
    suspend fun updateTypeExpense(@Path("id") id: Long, @Body input: TypeExpense)

    @DELETE("typeexpense/{id}")
    suspend fun deleteTypeExpense(@Path("id") id: Long)

    //endregion

    //region typeincome Service

    @GET("typeincome/")
    suspend fun listTypeIncome(): List<TypeIncome>

    @GET("typeincome/{id}")
    suspend fun findTypeIncome(@Path("id") id: Long): TypeIncome

    @POST("typeincome/")
    suspend fun createTypeIncome(@Body input: TypeIncome)

    @PUT("typeincome/{id}")
    suspend fun updateTypeIncome(@Path("id") id: Long, @Body input: TypeIncome)

    @DELETE("typeincome/{id}")
    suspend fun deleteTypeIncome(@Path("id") id: Long)

    //endregion

    //region user Service

    @GET("user/")
    suspend fun listUser(): List<User>

    @GET("user/{id}")
    suspend fun findUser(@Path("id") id: Long): User

    @GET("user/user/{userName}")
    suspend fun findUserName(@Path("userName") userName: String): User

    @POST("user/")
    suspend fun createUser(@Body input: UserRequest)

    @POST("user/login")
    suspend fun login(@Body input: LoginRequest): Boolean

    @PUT("user/{id}")
    suspend fun updateUser(@Path("id") id: Long, @Body input: UserRequest)

    @DELETE("user/{id}")
    suspend fun deleteUser(@Path("id") id: Long)


    //endregion
}

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8081/api/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}