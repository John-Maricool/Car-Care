package com.maricool.carcare.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCar(user: CarModel)

    @Query("select * from CarModel order by dateAdded desc")
    suspend fun getAllCarDetails(): List<CarModel>

    @Query("select * from CarModel where carId = :id")
    suspend fun getCar(id: Int): CarModel?

    @Query("delete from CarModel")
    suspend fun deleteAll()
}