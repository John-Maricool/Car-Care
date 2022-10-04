package com.maricool.carcare.usecases

import android.content.Context
import com.maricool.carcare.data.CarMapper
import com.maricool.carcare.data.entities.CarDetails
import com.maricool.carcare.data.source.local.CarDao
import com.maricool.carcare.data.source.local.CarModel
import com.maricool.carcare.utils.SharedPrefsImpl
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CheckupUsecase
@Inject constructor(val prefs: SharedPrefsImpl, val dao: CarDao){

    suspend fun getActiveCar(): CarDetails {
        val id = prefs.getSavedCarId()
        val car  = dao.getCar(id)
        return CarMapper.mapToEntity(car)
    }
}