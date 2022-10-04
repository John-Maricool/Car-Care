package com.maricool.carcare.ui.car_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maricool.carcare.data.entities.CarDetails
import com.maricool.carcare.usecases.MyCarUsecase
import com.maricool.carcare.utils.Error
import com.maricool.carcare.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MyCarViewModel
    @Inject constructor(val usecase: MyCarUsecase): ViewModel() {

    private val _error = MutableLiveData<Error>()
    val error: LiveData<Error> get() = _error
    private val _success = MutableLiveData<List<CarDetails>?>()
    val success: LiveData<List<CarDetails>?> get() = _success
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    val isLoadedSuccess = MutableLiveData<Boolean>()

        init {
            viewModelScope.launch {
                usecase.getSavedCars {
                    when (it) {
                        is Result.LoadingState -> {
                            _loading.postValue(it.isLoading)
                        }
                        is Result.ErrorResult -> {
                            isLoadedSuccess.value = false
                            _error.postValue(it.error)
                        }
                        is Result.Success -> {
                            isLoadedSuccess.value = true
                            _success.postValue(it.data)
                        }
                    }
                }
            }
        }
}