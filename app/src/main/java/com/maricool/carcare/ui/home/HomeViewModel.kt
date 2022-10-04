package com.maricool.carcare.ui.home

import androidx.lifecycle.ViewModel
import com.maricool.carcare.R
import com.maricool.carcare.data.entities.ServicesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject constructor(): ViewModel() {

        val services: ArrayList<ServicesItem> = arrayListOf(
            ServicesItem(img = R.drawable.request_service, title = R.string.request_service, desc = R.string.request_service_desc),
            ServicesItem(img = R.drawable.schedule, title = R.string.schedule, desc = R.string.schedule_desc),
            ServicesItem(img = R.drawable.track, title = R.string.track_car, desc = R.string.track_car_desc),
            ServicesItem(img = R.drawable.nearby, title = R.string.nearby, desc = R.string.nearby_desc),
            ServicesItem(img = R.drawable.billing, title = R.string.billing, desc = R.string.billing_desc),
            ServicesItem(img = R.drawable.support, title = R.string.support, desc = R.string.support_desc)
        )

    }