package com.example.middentest.data.model

data class userProfile(
    val name: List<String>,
    val email: String,
    val picture: List<String>,
    val gender: String,
    val registered: List<String>,
    val phone: String,
    val location: String, //TO DO CAMBIAR AL TIPO DE LA API
)