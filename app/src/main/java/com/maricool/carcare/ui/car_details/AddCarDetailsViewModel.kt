package com.maricool.carcare.ui.car_details

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maricool.carcare.R
import com.maricool.carcare.data.entities.CarDetails
import com.maricool.carcare.data.entities.Register
import com.maricool.carcare.usecases.SignupUsecase
import com.maricool.carcare.utils.Error
import com.maricool.carcare.utils.Event
import com.maricool.carcare.utils.Result
import com.maricool.carcare.utils.isTextValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCarDetailsViewModel
@Inject constructor(val usecase: SignupUsecase) : ViewModel() {

    val carModel = MutableLiveData<String>() // Two way
    val carColor = MutableLiveData<String>() // Two way
    val plateNumber = MutableLiveData<String>() // Two way
    val vinNumber = MutableLiveData<String>() //two way
    val area = MutableLiveData<String>() //two way

    val mIsCreatingAccount = MutableLiveData<Boolean>()
    val mSnackBarText = MutableLiveData<Event<@StringRes Int>>()
    val snackBarText: LiveData<Event<Int>> get() = mSnackBarText
    val imageStatus = MutableLiveData<String>("No Image uploaded")

    private val imageUri = MutableLiveData<Uri>()
    lateinit var signInDetails: Register
    lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private val _error = MutableLiveData<Error>()
    val error: LiveData<Error> get() = _error
    private val _success = MutableLiveData<Int?>()
    val success: LiveData<Int?> get() = _success
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading


    fun createAccountPressed() {
        if (!isTextValid(2, carModel.value)) {
            mSnackBarText.value = Event(R.string.car_model_err)
            return
        }
        if (!isTextValid(2, carColor.value)) {
            mSnackBarText.value = Event(R.string.car_color_err)
            return
        }
        if (!isTextValid(5, plateNumber.value)) {
            mSnackBarText.value = Event(R.string.car_plate_number_err)
            return
        }
        if (!isTextValid(5, vinNumber.value)) {
            mSnackBarText.value = Event(R.string.car_vin_number_err)
            return
        }
        if (!isTextValid(2, area.value)) {
            mSnackBarText.value = Event(R.string.car_area_err)
            return
        }
        if (imageUri.value == null) {
            mSnackBarText.value = Event(R.string.car_image_err)
            return
        }
        createAccount()
    }

    private fun createAccount() {
        mIsCreatingAccount.value = true
        val carDetails =
            CarDetails(
                carColor = carColor.value!!,
                model = carModel.value!!,
                plateNumber = plateNumber.value!!,
                area = area.value!!,
                vinNumber = vinNumber.value!!,
                image = imageUri.value.toString(),
            )
        viewModelScope.launch {
            usecase.signupUser(signInDetails, carDetails) {
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
                mIsCreatingAccount.value = false
            }
        }
    }


    fun goToGallery() {
        val intent =
            Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        intent.type = "image/*"
        resultLauncher.launch(intent)
    }

    fun updateImageUploaded() {
        imageStatus.value = "Image Upload Success"
    }

    fun updateImageNotUploaded() {
        imageStatus.value = "No Image Uploaded"
    }

    fun setNewImage(img: Uri) {
        Log.d("image", img.toString())
        imageUri.value = img
    }
}