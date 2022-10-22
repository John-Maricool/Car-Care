package com.maricool.carcare.usecases

import com.maricool.carcare.data.CarMapper
import com.maricool.carcare.data.entities.CarDetails
import com.maricool.carcare.data.source.local.CarDao
import com.maricool.carcare.utils.SharedPrefsImpl
import javax.inject.Inject

class CheckupUsecase
@Inject constructor(val prefs: SharedPrefsImpl, val dao: CarDao) {

    suspend fun getActiveCar(): CarDetails? {
        val id = prefs.getSavedCarId()
        val car = dao.getCar(id)
        return car?.let { CarMapper.mapToEntity(it) }
    }
}