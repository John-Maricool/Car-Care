package com.maricool.carcare.ui.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel
    @Inject constructor(): ViewModel() {

    private val _loginEvent = MutableLiveData<Unit>()
    private val _createAccountEvent = MutableLiveData<Unit>()

    val loginEvent: LiveData<Unit> = _loginEvent
    val createAccountEvent: LiveData<Unit> = _createAccountEvent

    fun goToLoginPressed() {
        _loginEvent.value = Unit
    }

    fun goToCreateAccountPressed() {
        _createAccountEvent.value = Unit
    }
}