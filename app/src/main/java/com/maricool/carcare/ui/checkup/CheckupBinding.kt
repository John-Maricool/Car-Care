package com.maricool.carcare.ui.checkup

import android.R
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter

@BindingAdapter("meeting_places")
fun AutoCompleteTextView.getMeetingPlacesAdapter(places: List<String>){
    val adapter =
        ArrayAdapter(this.context, R.layout.simple_dropdown_item_1line, places)
    threshold = 2
    setAdapter(adapter)
}

@BindingAdapter("payment_options")
fun AutoCompleteTextView.getPaymentOptionsAdapter(options: List<String>){
    val adapter =
        ArrayAdapter(this.context, R.layout.simple_dropdown_item_1line, options)
    threshold = 2
    setAdapter(adapter)
}