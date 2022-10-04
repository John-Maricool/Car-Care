package com.maricool.carcare.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maricool.carcare.R
import com.maricool.carcare.data.entities.Login
import com.maricool.carcare.usecases.LoginUsecase
import com.maricool.carcare.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(private val usecase: LoginUsecase) : ViewModel() {

    val emailText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()
    val mSnackBarText = MutableLiveData<Event<@androidx.annotation.StringRes Int>>()
    val snackBarText: LiveData<Event<Int>>
        get() = mSnackBarText
    val isLoggingIn = MutableLiveData<Boolean>()

    private val _error = MutableLiveData<Error>()
    val error: LiveData<Error> get() = _error
    private val _success = MutableLiveData<Int?>()
    val success: LiveData<Int?> get() = _success
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading


    private fun login() {
        isLoggingIn.value = true
        val login =
            Login(email = emailText.value!!, password = passwordText.value!!)
        viewModelScope.launch {
            usecase.loginUser(login) {
                when (it) {
                    is Result.LoadingState -> {
                        _loading.postValue(it.isLoading)
                    }
                    is Result.ErrorResult -> {
                        _error.postValue(it.error)
                    }
                    is Result.Success -> {
                        _success.postValue(it.data)
                    }
                }
                isLoggingIn.value = false
            }
        }
    }

    fun loginPressed() {
        if (!isEmailValid(emailText.value.toString().trim())) {
            mSnackBarText.postValue(Event(R.string.email_err))
            return
        }
        if (!isTextValid(6, passwordText.value)) {
            mSnackBarText.postValue(Event(R.string.password_err))
            return
        }
        login()
    }
}


