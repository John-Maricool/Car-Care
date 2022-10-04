package com.maricool.carcare.usecases

import android.content.Context
import android.util.Log
import com.maricool.carcare.R
import com.maricool.carcare.data.entities.CarDetails
import com.maricool.carcare.data.entities.Register
import com.maricool.carcare.data.repositories.CarLocalDataSource
import com.maricool.carcare.utils.ExceptionError
import com.maricool.carcare.utils.InternetError
import com.maricool.carcare.utils.Result
import com.maricool.carcare.utils.isConnected
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import javax.inject.Inject

class SignupUsecase
@Inject constructor(@ApplicationContext val context: Context, val source: CarLocalDataSource) {
    suspend fun signupUser(details: Register, carDetails: CarDetails, b: (Result<Int>) -> Unit) {
        if (context.isConnected()) {
            b.invoke(Result.LoadingState(true))
            try {
                source.insertCar(carDetails)
                Log.d("carDetails", carDetails.toString())
                delay(4000)
                b.invoke(Result.Success(R.string.success))
            } catch (e: Exception) {
                b.invoke(Result.ErrorResult(ExceptionError(error = R.string.err_message)))
            }
        } else {
            b.invoke(Result.ErrorResult(InternetError(error = R.string.internet_error)))
        }
        b.invoke(Result.LoadingState(false))
    }
}

