package com.maricool.carcare.ui.request_service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RequestViewModel
@Inject constructor() : ViewModel() {

    private val _checkupEvent = MutableLiveData<Int>()
    val checkupEvent: LiveData<Int> = _checkupEvent

    fun goToCheckupPressed(type: Int) {
        _checkupEvent.value = type
    }

}