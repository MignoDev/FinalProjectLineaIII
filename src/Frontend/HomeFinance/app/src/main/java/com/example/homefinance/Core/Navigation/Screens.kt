package com.example.homefinance.Core.Navigation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
data class Main(val userLoggedIn: Long)

@Serializable
object CreateProfile