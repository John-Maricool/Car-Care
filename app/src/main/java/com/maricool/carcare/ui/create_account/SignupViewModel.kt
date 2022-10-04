package com.maricool.carcare.ui.create_account

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maricool.carcare.R
import com.maricool.carcare.data.entities.Register
import com.maricool.carcare.utils.Event
import com.maricool.carcare.utils.isEmailValid
import com.maricool.carcare.utils.isTextValid
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel
@Inject constructor() : ViewModel() {

    val displayNameText = MutableLiveData<String>() // Two way
    val emailText = MutableLiveData<String>() // Two way
    val passwordText = MutableLiveData<String>() // Two way
    val secondPassword = MutableLiveData<String>() //two way
    val userPhone = MutableLiveData<String>() //two way

    val mIsCreatingAccount = MutableLiveData<Boolean>()
    val mSnackBarText = MutableLiveData<Event<@StringRes Int>>()
    val snackBarText: LiveData<Event<Int>> get() = mSnackBarText

    private val _validated = MutableLiveData<Register>()
    val validated: LiveData<Register> get() = _validated
    lateinit var register: Register

    private fun createAccount() {
        mIsCreatingAccount.value = true
        register =
            Register(
                displayNameText.value!!.trim(),
                passwordText.value!!.trim(),
                emailText.value!!.trim(),
                userPhone.value!!.trim()
            )
        _validated.postValue(register)
    }

    fun createAccountPressed() {
        if (!isTextValid(5, displayNameText.value)) {
            mSnackBarText.value = Event(R.string.name_err)
            return
        }

        if (!isEmailValid(emailText.value.toString().trim())) {
            mSnackBarText.value = Event(R.string.email_err)
            return
        }

        if (!isTextValid(6, passwordText.value) ||
            passwordText.value != secondPassword.value ||
            !isTextValid(6, passwordText.value)
        ) {
            mSnackBarText.value = Event(R.string.password_not_same)
            return
        }

        createAccount()
    }
}