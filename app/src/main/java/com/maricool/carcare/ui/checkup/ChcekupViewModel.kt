package com.maricool.carcare.ui.checkup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maricool.carcare.data.entities.CarDetails
import com.maricool.carcare.usecases.CheckupUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChcekupViewModel
@Inject constructor(val usecase: CheckupUsecase) : ViewModel() {

    val _car = MutableLiveData<CarDetails?>()
    val car: LiveData<CarDetails?> get() = _car

    val isNow = MutableLiveData<Boolean>(true)

    fun getCar() {
        viewModelScope.launch {
            _car.postValue(usecase.getActiveCar())
        }
    }

    fun toggleIsNow(){
        isNow.value = !isNow.value!!
    }
}