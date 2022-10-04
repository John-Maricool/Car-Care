package com.maricool.carcare.usecases

import com.maricool.carcare.data.entities.Login
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import android.content.Context
import com.maricool.carcare.R
import com.maricool.carcare.utils.ExceptionError
import com.maricool.carcare.utils.InternetError
import com.maricool.carcare.utils.Result
import com.maricool.carcare.utils.isConnected
import kotlinx.coroutines.delay

class LoginUsecase
    @Inject constructor(@ApplicationContext val context: Context){
        suspend fun loginUser(details: Login, b: (Result<Int>) -> Unit){
            if(context.isConnected()){
                b.invoke(Result.LoadingState(true))
                try{
                    delay(4000)
                    b.invoke(Result.Success(R.string.success))
                }catch (e: Exception){
                    b.invoke(Result.ErrorResult(ExceptionError(error = R.string.err_message)))
                }
            }else{
                b.invoke(Result.ErrorResult(InternetError(error = R.string.internet_error)))
            }
            b.invoke(Result.LoadingState(false))
        }
}