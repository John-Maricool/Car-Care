package com.maricool.carcare.ui.checkup

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
    val issue = MutableLiveData<String>()

    val isNow = MutableLiveData(true)

    var selectedDate: String? = null
    var selectedTime: String? = null

    val places = listOf(
        "Roadside", "Home", "I'm coming", "My location"
    )
    val paymemt_options = listOf(
        "Pay with cash", "Pay with card", "Pay on delivery"
    )

    val chosenPayment = MutableLiveData<String>()

    fun getCar() {
        viewModelScope.launch {
            _car.postValue(usecase.getActiveCar())
        }
    }

    fun toggleIsNow() {
        isNow.value = !isNow.value!!
    }
}