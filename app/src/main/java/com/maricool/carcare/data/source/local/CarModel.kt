package com.maricool.carcare.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Entity
class CarModel(
    @PrimaryKey(autoGenerate = true) var carId: Int = 0,
    val carModel: String,
    val carPlateNumber: String,
    val dateAdded: String = getDate(),
    val vinNumber: String,
    val carColor: String,
    val area: String,
    val carImage: String
)

private fun getDate(): String{
    var df: DateFormat = SimpleDateFormat("MMM d, yyyy")
    return df.format(Date())
}