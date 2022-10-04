package com.maricool.carcare.usecases

import com.maricool.carcare.R
import com.maricool.carcare.data.entities.CarDetails
import com.maricool.carcare.data.repositories.CarLocalDataSource
import com.maricool.carcare.utils.*
import kotlinx.coroutines.delay
import javax.inject.Inject

class MyCarUsecase
@Inject constructor(val source: CarLocalDataSource) {

    suspend fun getSavedCars(b: (Result<List<CarDetails>>) -> Unit) {
        b.invoke(Result.LoadingState(true))
        try {
            val result = source.getSavedCars()
            if (result.isEmpty()) {
                b.invoke(Result.ErrorResult(EmptyListError(error = R.string.err_message)))
            }else {
                b.invoke(Result.Success(result))
            }
            delay (1000)
        } catch (e: Exception) {
            b.invoke(Result.ErrorResult(ExceptionError(error = R.string.err_message)))
        }
    }
}