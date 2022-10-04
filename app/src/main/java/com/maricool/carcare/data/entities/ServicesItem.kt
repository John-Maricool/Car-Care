package com.maricool.carcare.data.entities

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class ServicesItem(
    @DrawableRes
    val img: Int,
    @StringRes
    val title: Int,
    @StringRes
    val desc: Int
)