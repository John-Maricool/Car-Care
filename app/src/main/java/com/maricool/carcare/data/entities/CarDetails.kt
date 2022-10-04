package com.maricool.carcare.data.entities

class CarDetails(
    val carId: Int = 0,
    val model: String,
    val carColor: String,
    val plateNumber: String,
    val vinNumber: String,
    val area: String,
    val image: String,
    val date: String = ""
)