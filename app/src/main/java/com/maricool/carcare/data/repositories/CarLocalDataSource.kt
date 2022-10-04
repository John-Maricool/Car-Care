package com.maricool.carcare.data.repositories

import com.maricool.carcare.data.CarMapper
import com.maricool.carcare.data.entities.CarDetails
import com.maricool.carcare.data.source.local.CarDao
import javax.inject.Inject

class CarLocalDataSource
@Inject constructor(val dao: CarDao){

    suspend fun insertCar(car: CarDetails){
        dao.addCar(CarMapper.mapToModel(car))
    }


    suspend fun getSavedCars(): List<CarDetails> {
        return CarMapper.mapAllToEntity(dao.getAllCarDetails())
    }
}