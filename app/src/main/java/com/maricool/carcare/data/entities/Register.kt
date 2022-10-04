package com.maricool.carcare.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class Register(
    val username: String,
    val emailAddress: String,
    val password: String,
    val phoneNumber: String
): Parcelable